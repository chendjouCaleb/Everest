package Everest.Mvc.ActionResultExecutor;

import Everest.Core.Exception.InputOutputException;
import Everest.Core.FileUtils;
import Everest.Http.HttpContext;
import Everest.Http.HttpResponse;
import Everest.Mvc.Result.LocalFileResult;

import java.io.*;

public class LocalFileResultExecutor implements IResultExecutor<LocalFileResult> {
    private static final int BUFFER_SIZE = 1024;

    public void execute(HttpContext httpContext, LocalFileResult result) {
        HttpResponse httpResponse = httpContext.getResponse();

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(result.getFile()), BUFFER_SIZE);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpResponse.outputStream(), BUFFER_SIZE);
        ) {


            String mimeType = FileUtils.getMimeType(bufferedInputStream);

            httpResponse.setContentType(mimeType);
            httpResponse.addHeader("Content-Length", String.valueOf(result.getFile().length()));
            httpResponse.addHeader("Content-Disposition", "inline; filename=\"" + result.getFile().getName() + "\"");

            byte[] buffer = new byte[BUFFER_SIZE];
            int length;

            while ((length = bufferedInputStream.read(buffer)) > 0) {
                bufferedOutputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw new InputOutputException(e);
        }
    }
}
