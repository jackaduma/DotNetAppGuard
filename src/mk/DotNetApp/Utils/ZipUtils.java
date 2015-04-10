package mk.DotNetApp.Utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

/**
 * @author makun
 *
 */
public class ZipUtils {
	private static final String TAG = "ZipUtils";

	/*
	 * 
	 */
	public static void ExtractAllWith7z(String srcZipFilePath, String desDirPath)
			throws Exception {
		String command = String.format("7z x -o%s %s", desDirPath, srcZipFilePath);
		Runtime.getRuntime().exec(command);
	}
	
	/*
	 * 
	 */
	public static void ExtractAllWithUnzip(String srcZipFilePath, String desDirPath) throws Exception {
		String command = String.format("unzip %s -d %s", srcZipFilePath, desDirPath);
		Runtime.getRuntime().exec(command);
	}

	/*
	 * include all sub-dirs and sub-files keep original tree struct
	 */
	public static void ExtractAll(String srcZipFilePath, String desDirPath)
			throws Exception {
		FileInputStream fis = new FileInputStream(srcZipFilePath);
		// ZipInputStream zis = new ZipInputStream(fis);
		BufferedInputStream bin = new BufferedInputStream(fis);
		ArchiveInputStream zis = new ArchiveStreamFactory()
				.createArchiveInputStream(ArchiveStreamFactory.ZIP, bin);
		// ZipInputStream zis = new ZipInputStream(bin);

		File fout = null;
		ArchiveEntry entry = null;
		byte[] buff = new byte[512];
		while ((entry = zis.getNextEntry()) != null) {
			if (entry.isDirectory()) {
				continue;
			}

			fout = new File(desDirPath, entry.getName());
			if (!fout.exists()) {
				(new File(fout.getParent())).mkdirs();
			}

			FileOutputStream out = new FileOutputStream(fout);
			BufferedOutputStream bout = new BufferedOutputStream(out);

			int count = zis.read(buff);
			while (count > 0) {
				bout.write(buff, 0, count);
				count = zis.read(buff);
			}

			System.out.println(String.format("%s: %s", TAG, "test"));
			bout.close();
			out.close();
		}
		bin.close();
		zis.close();
	}

	/*
	 * 
	 */
	public static void CompressFolder(String srcDirPath, String desZipFilePath)
			throws Exception {
		ZipOutputStream zipWriter = null;
		FileOutputStream fileWriter = null;

		fileWriter = new FileOutputStream(desZipFilePath);
		zipWriter = new ZipOutputStream(fileWriter);

		File[] files = new File(srcDirPath).listFiles();
		for (File file : files) {
			if (file.getName().startsWith(".")) {
				continue;
			}
			AddFolderToZip("", file.getAbsolutePath(), zipWriter);
		}
		zipWriter.flush();
		zipWriter.close();
	}

	public static void AddFileToZip(String path, String srcFile,
			ZipOutputStream zipWriter) throws Exception {
		File file = new File(srcFile);
		if (file.isDirectory()) {
			AddFolderToZip(path, srcFile, zipWriter);
		} else {
			byte[] buf = new byte[1024];
			int len;
			FileInputStream in = new FileInputStream(srcFile);
			zipWriter.putNextEntry(new ZipEntry(path + "/" + file.getName()));
			while ((len = in.read(buf)) > 0) {
				zipWriter.write(buf, 0, len);
			}
		}
	}

	public static void AddFolderToZip(String path, String srcFolder,
			ZipOutputStream zipWriter) throws Exception {
		File folder = new File(srcFolder);
		if (folder.isFile()) {
			AddFileToZip(path, srcFolder, zipWriter);
		} else {
			for (String fileName : folder.list()) {
				if (path.equals("")) {
					AddFileToZip(folder.getName(), srcFolder + "/" + fileName,
							zipWriter);
				} else {
					AddFileToZip(path + "/" + folder.getName(), srcFolder + "/"
							+ fileName, zipWriter);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		String appFilePath = "E:\\MK_Projects\\WinPhoneAppGuard\\WP7App\\Security_Dashboard.xap";
		String targetDirPath = "E:\\MK_Projects\\WinPhoneAppGuard\\WP7App\\test";
		ZipUtils.ExtractAll(appFilePath, targetDirPath);

		appFilePath = "E:\\MK_Projects\\WinPhoneAppGuard\\Win8Metro\\GoEverywhere_v1_2089.appx";
		targetDirPath = "E:\\MK_Projects\\WinPhoneAppGuard\\Win8Metro\\test";
		ZipUtils.ExtractAllWith7z(appFilePath, targetDirPath);
	}
}
