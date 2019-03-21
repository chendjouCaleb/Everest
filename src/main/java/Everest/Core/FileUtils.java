package Everest.Core;

import eu.medsea.mimeutil.MimeUtil;

import java.io.InputStream;
import java.util.Collection;

public class FileUtils {
    public static String getMimeType(InputStream stream){
        MimeUtil.registerMimeDetector("eu.medsea.mimeutil.detector.MagicMimeMimeDetector");
        Collection<?> mimeTypes = MimeUtil.getMimeTypes(stream);
        return mimeTypes.toString();
    }
}
