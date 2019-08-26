package com.lbin.util.mupdf;

import com.lbin.util.ConfigProperties;
import com.lbin.util.cmd.CommandLineUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * mupdf工具 pdf转图片
 */
public class MupdfUtil {

    /**
     *
     * @参数 pdfFile
     * @返回值 java.util.List<java.lang.String>
     * @开发者 lbin
     * @更新时间 2019/8/21 16:41
     * @描述 pdf转svg图片
     */
    public static List<String> onToSVG(String pdfFile) {
        List<String> svgFiles = new ArrayList<String>();
        try {
            String mutoolPath = ConfigProperties.getConfig("mupdf");
            File fullPath = new File(pdfFile);
            String parentPath = FilenameUtils.getFullPathNoEndSeparator(fullPath.getAbsolutePath());
            String name = FilenameUtils.getBaseName(pdfFile) + "_svg";
            File out = new File(parentPath, name + "_%d.svg");

            svgFiles = convert(mutoolPath, parentPath, fullPath.getAbsolutePath(), name, out.getAbsolutePath());

        } catch (Exception e) {
        }
        return svgFiles;
    }
    private static List<String> convert(String mutoolPath, String workDir, String fullPath, String name, String out) throws Exception {
        String cmd = String.format("%s convert -o %s %s", mutoolPath, out, fullPath);
        CommandLineUtils.executeCmd(cmd);
        File dir = new File(workDir);
        IOFileFilter fileFilter = FileFilterUtils.and(new PrefixFileFilter(name), new SuffixFileFilter(".svg"));
        Collection<File> svgs = FileUtils.listFiles(dir, fileFilter, TrueFileFilter.INSTANCE);
        return svgs.stream().map(File::getName).sorted(Comparator.comparingInt(new SvgFileNameKeyExtractor())).collect(Collectors.toList());
    }
}
