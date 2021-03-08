package vue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import application.MainQRCode;
import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import modele.Livre;

/**
 * La classe {@code ControleurQRCode} est la représentation graphique d'une
 * création de qrcode du livre.
 * 
 * @author Desebel MESNAGER
 *
 */
public class ControleurQRCode {

	private Livre livre;
	private String filePath;
	private String qrCodeFile = "/qrcode";

	public ControleurQRCode() {
	}

	@FXML
	private Label auteur;

	@FXML
	private Label titre;

	@FXML
	private Label statusQRCode;

	@FXML
	private ImageView imageView;

	@FXML
	private TextArea textArea;

	/**
	 * Cette méthode permet d'afficher le nom et prenom de l'auteur dans la fenêtre
	 * {@link MainQRCode}, il permet egalement de sauvegarder l'image dans chemin
	 * cité en format png et, il s'affiche aussi le status du livre.
	 */
	public void choixLivre(Livre livre) {
		this.livre = livre;
		if (livre != null) {
			auteur.setText(livre.getAuteur().getNom() + " " + livre.getAuteur().getPrenom());
			titre.setText(livre.getTitre());
			try {
				filePath = qrCodeFile + "/" + livre.getTitre() + ".png";
				File file = new File(filePath);
				if (file.exists()) {
					imageView.setImage(new Image(new FileInputStream(file)));
					statusQRCode.setText("QRCode créé pour cette oeuvre");

				} else {
					statusQRCode.setText("Pas de QRCode pour cette oeuvre");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Cette methode permet de saisir des textes et de générer du qrcode en format
	 * png d'un livre qui etait séléctionné dans {@link choixLivre}.
	 */
	@FXML
	private void genereQRCode() {
		String data = textArea.getText();
		textArea.setText("");

		System.out.println(data);
		if (!data.contentEquals("")) {

			try {
				int size = 100;
				BitMatrix bitMatrix = generateMatrix(livre.getTitre(), data, size);
				String imageFormat = "png";
				String outputFileName = qrCodeFile + "/" + livre.getTitre() + "." + imageFormat;
				File file = new File(qrCodeFile);
				if (!file.exists())
					file.mkdir(); // saving the qr image directly to the file.

				qrImage(outputFileName, imageFormat, bitMatrix);
				imageView.setImage(new Image(new FileInputStream(new File(outputFileName))));
				statusQRCode.setText("QRCode créé pour cette oeuvre");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Il permet de générer l'image de qrcode.
	 * 
	 * @param data   Les textes dans {@link textArea}.
	 * @param string Le titre du livre.
	 * @param size   La taille de l'image.
	 * @return qrcode
	 */
	private static BitMatrix generateMatrix(String data, String string, int size) {
		BitMatrix bitMatrix = null;
		try {
			bitMatrix = new QRCodeWriter().encode(data, BarcodeFormat.QR_CODE, size, size);
		} catch (WriterException e) {
			e.printStackTrace();
		}
		return bitMatrix;
	}

	/**
	 * Cette methode est appelé pour sauvegarder le qrcode en format png.
	 * 
	 * @param outputFileName fichier finale.
	 * @param imageFormat    format de l'image.
	 * @param bitMatrix
	 */
	private void qrImage(String outputFileName, String imageFormat, BitMatrix bitMatrix) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileName));
			MatrixToImageWriter.writeToStream(bitMatrix, imageFormat, fileOutputStream);
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Cette methode permet d'imprimer le qrcode en cliquant le button "Imprimer".
	 */
	@FXML
	private void printQrCode() {
		File file = new File(qrCodeFile);
		if (file.exists()) {
			printImageView();
		}
	}

	/**
	 * Cette méthode appelle une boite de dialogue pour paramétrer l'impression de
	 * l'image et imprime l'image.
	 */
	private void printImageView() {
		PrinterJob printerJob = PrinterJob.createPrinterJob();
		printerJob.showPrintDialog(imageView.getScene().getWindow());
		if (printerJob != null) {
			boolean success = printerJob.printPage(imageView);
			if (success) {
				printerJob.endJob();
			}
		}

	}

}
