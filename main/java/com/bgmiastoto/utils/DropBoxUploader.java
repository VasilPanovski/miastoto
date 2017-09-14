package com.bgmiastoto.utils;

import com.bgmiastoto.entities.dropbox.DropBoxConnectionInfo;
import com.bgmiastoto.services.ConnectionInfoService;
import com.dropbox.core.http.OkHttp3Requestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

@Component
public class DropBoxUploader {

    private static ConnectionInfoService connectionInfoService;

    @Autowired
    public DropBoxUploader(ConnectionInfoService connectionInfoService) {
        DropBoxUploader.connectionInfoService = connectionInfoService;
    }

    public static String uploadImage(String imageName, MultipartFile file) throws DbxException {
        if (file == null || file.isEmpty()){
            return null;
        }
        DbxRequestConfig config = new DbxRequestConfig("bgmiastoto");
        DropBoxConnectionInfo dropBoxConnectionInfo = connectionInfoService.getDropBoxInfo();
        DbxClientV2 client = new DbxClientV2(config, dropBoxConnectionInfo.getSecretKey());

        FullAccount account = client.users().getCurrentAccount();

        // Get files and folder metadata from Dropbox root directory
        ListFolderResult result = client.files().listFolder("/miastoto/");
        while (true) {
            for (Metadata metadata : result.getEntries()) {
                String pathLower = metadata.getPathLower();
                if (pathLower.equals("/miastoto/" + imageName.toLowerCase())){
                    client.files().delete("/miastoto/" + imageName);
                }

            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }
        // Upload to Dropbox
        try (InputStream in = new ByteArrayInputStream(file.getBytes())) {

            FileMetadata metadata = client.files().uploadBuilder("/miastoto/" + imageName)
                    .uploadAndFinish(in);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return client.files().getTemporaryLink("/miastoto/" + imageName).getLink();
    }
}

