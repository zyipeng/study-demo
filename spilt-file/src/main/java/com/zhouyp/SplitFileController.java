package com.zhouyp;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.util.FileUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhouyp
 */
@Slf4j
@RestController
public class SplitFileController {

    private static final String DATA_DIR = "tmp/";
    private static final ConcurrentHashMap<String, File> MD5_CACHE = new ConcurrentHashMap<>();

    @PostMapping("/upload")
    public Boolean upload(String name,
                          String md5Val,
                          Long size,
                          Long chunkSize,
                          Integer chunks,
                          Integer chunkNo,
                          @RequestParam("file") MultipartFile multipartFile) throws IOException {
        log.info("file upload...");
        //收件将分片文件放到应用缓存中
        File cacheFile = MD5_CACHE.get(md5Val);
        if (cacheFile == null) {
            cacheFile = new File(DATA_DIR, UUID.randomUUID().toString() + name.substring(name.indexOf('.')));
            log.info(cacheFile.getAbsolutePath());
            cacheFile.getParentFile().mkdir();
            MD5_CACHE.put(md5Val, cacheFile);
        }
        RandomAccessFile rw = new RandomAccessFile(cacheFile, "rw");
        boolean finished = chunkNo.equals(chunks);
        rw.seek((chunkNo - 1) * chunkSize);
        rw.write(multipartFile.getBytes());
        rw.close();
        if (finished) {
            log.info("success");
            MD5_CACHE.remove(md5Val);
            return true;
        }
        return false;
    }

    @GetMapping("play")
    public void play(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.reset();
        //TODO 测试之前需要在项目根目录下的tmp文件夹放置对应的mp4文件
        File file = new File("tmp/tmp-video.mp4");
        long fileLength = file.length();
        // 随机读文件
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");

        //获取从那个字节开始读取文件
        String rangeString = request.getHeader("Range");
        long range=0;

        if (!StringUtils.isEmpty(rangeString)) {
            range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
        }
        //获取响应的输出流
        OutputStream outputStream = response.getOutputStream();
        //设置内容类型
        response.setHeader("Content-Type", "video/mp4");
        //返回码需要为206，代表只处理了部分请求，响应了部分数据
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);

        // 移动访问指针到指定位置
        randomAccessFile.seek(range);
        // 每次请求只返回1MB的视频流
        byte[] bytes = new byte[1024 * 1024];
        int len = randomAccessFile.read(bytes);
        //设置此次相应返回的数据长度
        response.setContentLength(len);
        //设置此次相应返回的数据范围
        response.setHeader("Content-Range", "bytes "+range+"-"+(fileLength-1)+"/"+fileLength);
        // 将这1MB的视频流响应给客户端
        outputStream.write(bytes, 0, len);
        outputStream.close();
        randomAccessFile.close();

        System.out.println("返回数据区间:【"+range+"-"+(range+len)+"】");
    }
}
