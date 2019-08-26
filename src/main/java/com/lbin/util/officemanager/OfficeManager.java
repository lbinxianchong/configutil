package com.lbin.util.officemanager;

import com.lbin.util.ConfigProperties;
import org.apache.commons.io.FilenameUtils;
import org.jodconverter.LocalConverter;
import org.jodconverter.office.LocalOfficeManager;
import org.jodconverter.office.OfficeException;
import org.jodconverter.office.OfficeUtils;

import java.io.File;

/**
 * OfficeManager转码工具 文档转pdf
 */
public class OfficeManager {
    private static final LocalOfficeManager officeManager = LocalOfficeManager.builder().officeHome(ConfigProperties.getConfig("officemanager")).build();
    /**
     *
     * @param
     * @return void
     * @date 2019/8/26 15:15
     * @Description 初始化OfficeManager
     */
    public static void startOfficeManager() {
        try {
            officeManager.start();
            boolean is = officeManager.isRunning();
        } catch (OfficeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     *
     * @param
     * @return void
     * @date 2019/8/26 15:16
     * @Description 停止officeManager
     */
    public static void destroy() {
        OfficeUtils.stopQuietly(officeManager);
    }

    /**
     *
     * @param file 文档转码
     * @return java.lang.String
     * @date 2019/8/26 15:16
     * @Description
     */
    public static String onToPDF(String file) {
        try {
            String baseName = FilenameUtils.getBaseName(file);
            File inputFile = new File(file);
            String parentPath = FilenameUtils.getFullPathNoEndSeparator(inputFile.getAbsolutePath());
            File outputFile = new File(parentPath, baseName + ".pdf");

            LocalConverter.builder().officeManager(officeManager)
                    .build()
                    .convert(inputFile)
                    .to(outputFile)
                    .execute();
            return outputFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    static {
        startOfficeManager();
    }
    public static void main(String[] args) {
        onToPDF("C:\\Users\\frosoft\\Desktop\\测试\\测试A2.doc");
    }
    public int onDestroy() {
        destroy();
        return 0;
    }
}
