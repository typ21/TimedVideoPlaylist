package typ21;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;

public class Brain {

	private Screen screen;

	Brain(String stringPath) {
		String workingDirectory = System.getProperty("user.dir");

		File file = new File(workingDirectory, stringPath);
//		System.out.println(file.getParent());
//		System.out.println(file.getAbsolutePath());

		NodeList videoNList = processTvlFile(file);

//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
		screen = new Screen();
		screen.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});

		for (int i = 0; i < videoNList.getLength(); i++) {
			Element element = (Element) videoNList.item(i);

			String filename = element.getElementsByTagName("file").item(0).getTextContent();
			String begin = element.getElementsByTagName("begin").item(0).getTextContent();
			String end = element.getElementsByTagName("end").item(0).getTextContent();

			String[] bSplit = begin.split(":");
			long bTime = Integer.parseInt(bSplit[0]) * 60 * 60 * 1000 + Integer.parseInt(bSplit[1]) * 60 * 1000
					+ Integer.parseInt(bSplit[2]) * 1000 + Integer.parseInt(bSplit[3]);

			String[] eSplit = end.split(":");
			long eTime = Integer.parseInt(eSplit[0]) * 60 * 60 * 1000 + Integer.parseInt(eSplit[1]) * 60 * 1000
					+ Integer.parseInt(eSplit[2]) * 1000 + Integer.parseInt(eSplit[3]);

			screen.play(new File(file.getParent(), filename), bTime, eTime);

			try {
				Thread.sleep(eTime - bTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		screen.exitProgram();
	}

	Brain() {
		System.out.println("Nothing!");
	}

	private NodeList processTvlFile(File file) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

		Document document = null;
		try {
			document = builder.parse(file);
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}

		document.getDocumentElement().normalize();

//		Element root = document.getDocumentElement();
//		System.out.println(root.getNodeName());

		NodeList nListVideos = document.getElementsByTagName("video");

		return nListVideos;
	}
}
