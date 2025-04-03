package org.homerunball.pillmein.voice.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.UUID;

public class AudioConvertUtil {

    public static File multipartToFile(org.springframework.web.multipart.MultipartFile multipartFile) throws IOException {
        String extension = getExtension(multipartFile.getOriginalFilename());
        String safeFileName = UUID.randomUUID() + (extension != null ? "." + extension : "");

        File tempFile = new File(System.getProperty("java.io.tmpdir"), safeFileName);
        multipartFile.transferTo(tempFile);

        return tempFile;
    }

    private static String getExtension(String filename) {
        if (filename == null) return null;
        int dotIndex = filename.lastIndexOf('.');
        return (dotIndex >= 0) ? filename.substring(dotIndex + 1) : null;
    }

    public static File convertM4aToMp3(File m4aFile) throws IOException, InterruptedException {
        String mp3FileName = UUID.randomUUID() + ".mp3";
        File mp3File = new File(System.getProperty("java.io.tmpdir"), mp3FileName);

        String ffmpegPath = "ffmpeg";
        String command = String.format("%s -y -i %s %s",
                ffmpegPath,
                m4aFile.getAbsolutePath(),
                mp3File.getAbsolutePath());

        System.out.println("[ffmpeg CMD] " + command);

        Process process = Runtime.getRuntime().exec(command);

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        while ((line = errorReader.readLine()) != null) {
            System.err.println("[ffmpeg] " + line);
        }

        int exitCode = process.waitFor();

        if (exitCode != 0) {
            throw new RuntimeException("ffmpeg 변환 실패 (exitCode=" + exitCode + ")");
        }

        return mp3File;
    }
}
