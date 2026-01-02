package com.yunshen.yunshoppingbackend.controller;

import cn.hutool.core.util.StrUtil;
import com.yunshen.yunshoppingbackend.annotation.AuthCheck;
import com.yunshen.yunshoppingbackend.common.BaseResponse;
import com.yunshen.yunshoppingbackend.common.ErrorCode;
import com.yunshen.yunshoppingbackend.common.ResultUtils;
import com.yunshen.yunshoppingbackend.exception.BusinessException;
import com.yunshen.yunshoppingbackend.common.DeleteRequest;
import com.yunshen.yunshoppingbackend.manager.CosManager;
import com.yunshen.yunshoppingbackend.manager.upload.FilePictureUpload;
import com.yunshen.yunshoppingbackend.model.entity.User;
import com.yunshen.yunshoppingbackend.model.vo.UploadPictureResult;
import com.yunshen.yunshoppingbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 文件上传接口
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private FilePictureUpload filePictureUpload;

    @Resource
    private CosManager cosManager;

    @Resource
    private UserService userService;

    /**
     * 文件上传
     *
     * @param multipartFile 文件
     * @param request       请求
     * @return 上传结果
     */
    @PostMapping("/upload")
    public BaseResponse<UploadPictureResult> uploadPicture(
            @RequestPart("file") MultipartFile multipartFile,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        log.info("文件上传 uid:{} fileName:{}", loginUser.getId(), multipartFile.getOriginalFilename());

        // 上传文件默认保存在用户目录下
        String uploadPathPrefix = String.format("user/%s", loginUser.getId());
        UploadPictureResult uploadPictureResult = filePictureUpload.uploadPicture(multipartFile, uploadPathPrefix);

        return ResultUtils.success(uploadPictureResult);
    }

    /**
     * 上传商品图片
     *
     * @param multipartFile 文件
     * @param request       请求
     * @return 上传结果
     */
    @PostMapping("/upload/product")
    @AuthCheck(mustRole = "seller")
    public BaseResponse<UploadPictureResult> uploadProductPicture(
            @RequestPart("file") MultipartFile multipartFile,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        log.info("商品图片上传 uid:{} fileName:{}", loginUser.getId(), multipartFile.getOriginalFilename());

        // 商品图片保存在product目录下
        String uploadPathPrefix = "product";
        UploadPictureResult uploadPictureResult = filePictureUpload.uploadPicture(multipartFile, uploadPathPrefix);

        return ResultUtils.success(uploadPictureResult);
    }

    /**
     * 上传店铺图片
     *
     * @param multipartFile 文件
     * @param request       请求
     * @return 上传结果
     */
    @PostMapping("/upload/shop")
    @AuthCheck(mustRole = "seller")
    public BaseResponse<UploadPictureResult> uploadShopPicture(
            @RequestPart("file") MultipartFile multipartFile,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        log.info("店铺图片上传 uid:{} fileName:{}", loginUser.getId(), multipartFile.getOriginalFilename());

        // 店铺图片保存在shop目录下
        String uploadPathPrefix = "shop";
        UploadPictureResult uploadPictureResult = filePictureUpload.uploadPicture(multipartFile, uploadPathPrefix);

        return ResultUtils.success(uploadPictureResult);
    }

    /**
     * 上传用户头像
     *
     * @param multipartFile 文件
     * @param request       请求
     * @return 上传结果
     */
    @PostMapping("/upload/avatar")
    public BaseResponse<UploadPictureResult> uploadAvatar(
            @RequestPart("file") MultipartFile multipartFile,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        log.info("用户头像上传 uid:{} fileName:{}", loginUser.getId(), multipartFile.getOriginalFilename());

        // 用户头像保存在avatar目录下
        String uploadPathPrefix = "avatar";
        UploadPictureResult uploadPictureResult = filePictureUpload.uploadPicture(multipartFile, uploadPathPrefix);

        return ResultUtils.success(uploadPictureResult);
    }

    /**
     * 删除文件
     *
     * @param deleteRequest 删除请求
     * @param request       请求
     * @return 删除结果
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteFile(@RequestBody DeleteRequest deleteRequest,
                                             HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        Long id = deleteRequest.getId();
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 这里的id应该是文件URL或者key
        // 由于DeleteRequest使用Long类型不合适传URL所以这里暂时不处理实际的删除逻辑
        // 建议创建专门的DeleteFileRequest包含fileUrl字段

        log.info("文件删除 uid:{} fileId:{}", loginUser.getId(), id);
        return ResultUtils.success(true);
    }

    /**
     * 根据文件URL删除文件
     *
     * @param fileUrl 文件URL
     * @param request 请求
     * @return 删除结果
     */
    @PostMapping("/deleteByUrl")
    public BaseResponse<Boolean> deleteFileByUrl(@RequestParam("fileUrl") String fileUrl,
                                                   HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }

        if (StrUtil.isBlank(fileUrl)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "文件URL不能为空");
        }

        log.info("根据URL删除文件 uid:{} fileUrl:{}", loginUser.getId(), fileUrl);

        try {
            // 从URL中提取文件key
            String fileKey = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            cosManager.deleteObject(fileKey);
            return ResultUtils.success(true);
        } catch (Exception e) {
            log.error("删除文件失败 uid:{} fileUrl:{}", loginUser.getId(), fileUrl, e);
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除文件失败");
        }
    }
}