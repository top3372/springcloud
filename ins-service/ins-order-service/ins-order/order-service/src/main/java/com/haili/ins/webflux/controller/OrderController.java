package com.haili.ins.webflux.controller;


import com.haili.ins.common.cloud.version.annotation.ApiVersion;
import com.haili.ins.common.cloud.version.annotation.UrlVersion;
import com.haili.ins.common.utils.JSONUtil;
import com.haili.ins.feign.MemberFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Slf4j
@RestController
@RequestMapping("/order")
@UrlVersion("1")
public class OrderController {

    @javax.annotation.Resource
    private MemberFeign memberFeign;

    @GetMapping("/hello")
    @UrlVersion("1.2")
    public Mono<String> hello() {

        log.info("info: " + JSONUtil.toJson(memberFeign.info()));
        log.info("info2: " + JSONUtil.toJson(memberFeign.info2()));
        // 【改】返回类型为Mono<String>
        return Mono.just("Welcome to reactive world ~");
        // 【改】使用Mono.just生成响应式数据
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> requestBodyFlux(@RequestPart("file") FilePart filePart) throws IOException {

        System.out.println(filePart.filename());
        Path tempFile = Files.createTempFile("xttblog", filePart.filename());

        //NOTE 方法一
        AsynchronousFileChannel channel =
                AsynchronousFileChannel.open(tempFile, StandardOpenOption.WRITE);
        DataBufferUtils.write(filePart.content(), channel, 0)
                .doOnComplete(() -> {
                    System.out.println("finish");
                })
                .subscribe();

        //NOTE 方法二
        //filePart.transferTo(tempFile.toFile());

        System.out.println(tempFile.toString());
        return Mono.just(filePart.filename());
    }

    @GetMapping("/download")
    public Mono<Void> downloadByWriteWith(ServerHttpResponse response) throws IOException {
        ZeroCopyHttpOutputMessage zeroCopyResponse = (ZeroCopyHttpOutputMessage) response;
        response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=xttblog.png");
        response.getHeaders().setContentType(MediaType.IMAGE_PNG);

        Resource resource = new ClassPathResource("xttblog.png");
        File file = resource.getFile();
        return zeroCopyResponse.writeWith(file, 0, file.length());
    }
}
