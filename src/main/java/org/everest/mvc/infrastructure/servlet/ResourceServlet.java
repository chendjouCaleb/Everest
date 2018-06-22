package org.everest.mvc.infrastructure.servlet;

import eu.medsea.mimeutil.MimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;

public class ResourceServlet extends HttpServlet{
    private Logger logger = LoggerFactory.getLogger(ResourceServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.info("file URI: " + req.getRequestURI());
        String[] segment = req.getRequestURI().split("/");
        String fileName = segment[segment.length - 1];
        resp.setHeader("Content-Disposition", "inline;" + fileName);
        ServletContext context = req.getServletContext();

        InputStream inputStream = context.getResourceAsStream(req.getRequestURI());

        if (inputStream == null) {
            String message = "Le fichier demandé est inexistant. Code Erreur: " + HttpServletResponse.SC_NOT_FOUND;
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, message);
            return;
        }

        String mime = req.getServletContext().getMimeType(req.getRequestURI());
//        if (mime == null){
//            mime = "application/octet-stream";
//        }
        resp.setContentType(mime);
        int read;
        byte[] bytes = new byte[1024];
        OutputStream outputStream = resp.getOutputStream();

        while ((read = inputStream.read(bytes)) != -1){
            outputStream.write(bytes, 0, read);
        }

        inputStream.reset();
        outputStream.flush();
        outputStream.close();
    }

    public String getMimeType(InputStream stream){
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        Collection<?> mimeTypes = MimeUtil.getMimeTypes(stream);
        return mimeTypes.toString();
    }
}
