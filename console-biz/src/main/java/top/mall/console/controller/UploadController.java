package top.mall.console.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.mall.console.utils.FastDFSClient;
import top.mall.console.utils.RequestErrorCode;
import top.mall.pojo.RpcResult;


@RestController
@RequestMapping("/upload")
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private String file_server_url;

    @RequestMapping("/uploadFile")
    public RpcResult<String> uploadFile(@RequestParam("file") MultipartFile file) {
        System.out.println("上传文件");
        // 获得文件名:
        String fileName = file.getOriginalFilename();
        // 获得文件的扩展名:
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 创建工具类
        FastDFSClient client = null;
        try {
            client = new FastDFSClient("classpath:fastDFS/fdfs_client.conf");
            String path = client.uploadFile(file.getBytes(), extName); // group1/M00/
            String url = file_server_url + path;
            return RpcResult.success(url);
        } catch (Exception e) {
            e.printStackTrace();
            return RpcResult.error(RequestErrorCode.UPLOAD_IMG_ERROR, "图片上传失败");
        }
    }
}
