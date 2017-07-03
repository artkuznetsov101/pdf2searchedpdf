package com.pdf2searchedpdf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sourceforge.tess4j.ITesseract.RenderedFormat;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;
import net.sourceforge.tess4j.util.PdfUtilities;

public class App {
	public static void main(String[] args) throws IOException, TesseractException {

		// extract
		File tessDataFolder = LoadLibs.extractTessResources("tessdata");

		// init
		Tesseract tessaractInstance = new Tesseract();

		Date startDate = new Date();

		// convert pdf to tiff
		File tiffFile = PdfUtilities.convertPdf2Tiff(new File("sample.pdf"));

		// convert pdf to searched pdf
		List<RenderedFormat> list = new ArrayList<>();
		list.add(RenderedFormat.PDF);
		tessaractInstance.setLanguage("eng");
		tessaractInstance.setDatapath(tessDataFolder.getParent());
		tessaractInstance.createDocuments(tiffFile.getAbsolutePath(), "sample-searched", list);

		Date endDate = new Date();

		System.out.println(
				"\npdf to searched pdf takes " + (int) ((endDate.getTime() - startDate.getTime()) / 1000) + " sec \n");
	}
}