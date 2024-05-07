/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlynhansu_bst_lvtn;

import BST.*;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.sql.SQLException;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Font;
import com.toedter.calendar.*;

/**
 *
 * @author itsHuaan
 */
public class GenericClass {

    public static String maNhanVien, hoTen;
    public static boolean loaiNguoiDung;
    public static boolean isQuanLy = false;

    public static void clearSession() {
        maNhanVien = null;
        hoTen = null;
        loaiNguoiDung = false;
    }

    public static void encryptPassword(JTable jTable, int colIndex) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(getMd5(model.getValueAt(i, colIndex).toString()), i, colIndex);
        }
    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateRandomString(String prefix, int min, int max) {
        Random random = new Random();
        int randomNumber = random.nextInt(max - min + 1) + min;
        String randomString = prefix.toUpperCase() + randomNumber;
        return randomString;
    }

    public static <T extends Comparable<T>> void getTreeData(Node<T> root, DefaultTableModel model, Function<T, Object> extractor) {
        if (root != null) {
            getTreeData(root.left, model, extractor);
            model.addRow((Object[]) extractor.apply(root.data));
            getTreeData(root.right, model, extractor);
        }
    }

    public static <T extends Comparable<T>> void loadComboboxFromTree(Node<T> root, DefaultComboBoxModel model, Function<T, String> extractor) {
        if (root != null) {
            loadComboboxFromTree(root.left, model, extractor);
            model.addElement(extractor.apply(root.data));
            loadComboboxFromTree(root.right, model, extractor);
        }
    }

    public static void tableFilter(JTable jTable, int columnIndex, String regex, SortOrder sortOrder) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("\\b" + regex + "\\b"));
        sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(columnIndex, sortOrder)));
    }

    public static void loadCombobox(DefaultComboBoxModel model, int minVal, int maxVal) {
        model.removeAllElements();
        for (int i = maxVal; i >= minVal; i--) {
            model.addElement(i);
        }
    }

    public static void tinhThue(JTable jTable, int valueColumn) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            int rowValue = (int) model.getValueAt(i, valueColumn);
            double value = rowValue > 10000000 ? rowValue * 0.1 : 0;
            model.setValueAt((int) value, i, model.getColumnCount() - 1);
        }
    }

    public static void centerOnScreen(JFrame frame) {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();
        GraphicsDevice secondaryScreen = null;

        if (screens.length > 1) {
            secondaryScreen = screens[0];
        }

        if (secondaryScreen != null) {
            Dimension size = frame.getSize();
            Rectangle secondaryBounds = secondaryScreen.getDefaultConfiguration().getBounds();
            int x = secondaryBounds.x + (int) (secondaryBounds.getWidth() - size.getWidth()) / 2;
            int y = secondaryBounds.y + (int) (secondaryBounds.getHeight() - size.getHeight()) / 2;
            frame.setBounds(x, y, size.width, size.height);
        } else {
            Rectangle bounds = screens[0].getDefaultConfiguration().getBounds();
            int x = (int) ((bounds.getWidth() - frame.getWidth()) / 2);
            int y = (int) ((bounds.getHeight() - frame.getHeight()) / 2);
            frame.setLocation(x, y);
        }
    }

    public static ResultSet getResultSet(Connection connection, String query) {
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultSet;
    }

    public static void numbersOnly(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '.') {
            evt.consume();
        }
    }

    public static void charactersOnly(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) && c != ' ') {
            evt.consume();
        }
    }

    public static void modifyDB(Connection connection, String query, JFrame jFrame, String message) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            JOptionPane.showMessageDialog(jFrame, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(jFrame, "Có lỗi xảy ra\nVui lòng thử lại", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Object getParameter(ResultSet resultSet) {
        Object parameter = null;
        try {
            while (resultSet.next()) {
                parameter = resultSet.getObject(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuanLyChamCong.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parameter;
    }

    public static void sortTableByLastName(JTable jTable, int column, SortOrder sortOrder) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        Comparator<Object> lastNameComparator = (obj1, obj2) -> {
            String fullName1 = obj1.toString();
            String fullName2 = obj2.toString();
            String[] words1 = fullName1.split("\\s");
            String[] words2 = fullName2.split("\\s");
            String lastName1 = words1.length > 1 ? words1[words1.length - 1] : words1[0];
            String lastName2 = words2.length > 1 ? words2[words2.length - 1] : words2[0];
            Collator collator = Collator.getInstance(new Locale("vi", "VN"));
            return collator.compare(lastName1, lastName2);
        };
        sorter.setComparator(column, lastNameComparator);
        sorter.setSortable(column, true);
        sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(column, sortOrder)));
        jTable.setRowSorter(sorter);
        model.setRowCount(0);
    }

    public static void addObjectsToTable(JTable jTable, int column, SortOrder sortOrder) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        sorter.setSortable(column, true);
        sorter.setSortKeys(java.util.List.of(new RowSorter.SortKey(column, sortOrder)));
        jTable.setRowSorter(sorter);
        model.setRowCount(0);
    }

    public static void setImage(JLabel jLabel, URL imgLink) {
        ImageIcon icon = new ImageIcon(imgLink);
        Image scaledImage = icon.getImage().getScaledInstance(jLabel.getWidth(), jLabel.getHeight(), Image.SCALE_SMOOTH);
        jLabel.setIcon(new ImageIcon(scaledImage));
    }

    public static void openFile(String filePath) {
        try {
            File path = new File(filePath);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// <editor-fold defaultstate="collapsed" desc="Generated Code">

    /*public static void exportToExcel(JFileChooser jFileChooser, JTable jTable, String sheetName, String tableTitle, String formula) {
        try {
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                FileOutputStream outputStream;
                try (Workbook workbook = new XSSFWorkbook()) {
                    Sheet sheet = workbook.createSheet(sheetName);
                    // Title Style
                    CellStyle titleStyle = workbook.createCellStyle();
                    titleStyle.setAlignment(HorizontalAlignment.CENTER);
                    org.apache.poi.ss.usermodel.Font titleFont = workbook.createFont();
                    titleFont.setBold(true);
                    titleFont.setFontHeightInPoints((short) 16);
                    titleStyle.setFont(titleFont);
                    // Header Style
                    CellStyle headerCellStyle = workbook.createCellStyle();
                    headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
                    headerCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
                    headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    headerCellStyle.setBorderTop(BorderStyle.THIN);
                    headerCellStyle.setBorderBottom(BorderStyle.THIN);
                    headerCellStyle.setBorderLeft(BorderStyle.THIN);
                    headerCellStyle.setBorderRight(BorderStyle.THIN);
                    org.apache.poi.ss.usermodel.Font headerFont = workbook.createFont();
                    headerFont.setBold(true);
                    headerCellStyle.setFont(headerFont);
                    // Data Style
                    CellStyle dataCellStyle = workbook.createCellStyle();
                    dataCellStyle.setBorderTop(BorderStyle.THIN);
                    dataCellStyle.setBorderBottom(BorderStyle.THIN);
                    dataCellStyle.setBorderLeft(BorderStyle.THIN);
                    dataCellStyle.setBorderRight(BorderStyle.THIN);

                    // Create a data format for numbers
                    DataFormat dataFormat = workbook.createDataFormat();
                    CellStyle numberCellStyle = workbook.createCellStyle();
                    numberCellStyle.cloneStyleFrom(dataCellStyle);
                    numberCellStyle.setDataFormat(dataFormat.getFormat("###0")); // Customize the format as needed

                    // Company Info
                    Row companyInfoRow1 = sheet.createRow(0);
                    Cell companyNameCell = companyInfoRow1.createCell(0);
                    companyNameCell.setCellValue("Tên công ty");
                    Row companyInfoRow2 = sheet.createRow(1);
                    Cell companyAddressCell = companyInfoRow2.createCell(0);
                    companyAddressCell.setCellValue("Địa chỉ");
                    Row companyInfoRow3 = sheet.createRow(2);
                    Cell companyHotlineCell = companyInfoRow3.createCell(0);
                    companyHotlineCell.setCellValue("Hotline");
                    // Title Row
                    Row titleRow = sheet.createRow(3);
                    Cell titleCell = titleRow.createCell(0);
                    titleCell.setCellValue(tableTitle);
                    titleCell.setCellStyle(titleStyle);
                    sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, jTable.getColumnCount() - 1));
                    // Header Row
                    Row headerRow = sheet.createRow(4);
                    for (int i = 0; i < jTable.getColumnCount(); i++) {
                        Cell cell = headerRow.createCell(i);
                        cell.setCellValue(jTable.getColumnName(i));
                        cell.setCellStyle(headerCellStyle);
                    }
                    // Populating data and applying formatting for each cell
                    for (int j = 0; j < jTable.getRowCount(); j++) {
                        Row row = sheet.createRow(j + 5); // Offset by 5 to skip the company info, title, and header rows
                        for (int k = 0; k < jTable.getColumnCount(); k++) {
                            Cell cell = row.createCell(k);
                            if (jTable.getValueAt(j, k) != null) {
                                if (jTable.getValueAt(j, k) instanceof Number) {
                                    cell.setCellValue(((Number) jTable.getValueAt(j, k)).doubleValue());
                                    cell.setCellStyle(numberCellStyle); // Apply number cell style
                                } else {
                                    cell.setCellValue(jTable.getValueAt(j, k).toString());
                                    cell.setCellStyle(dataCellStyle);
                                }
                            }
                        }
                    }
                    // Auto-size columns (optional)
                    for (int i = 0; i < jTable.getColumnCount(); i++) {
                        sheet.autoSizeColumn(i);
                    }
                    // Sum function
                    Row sumRow = sheet.createRow(jTable.getRowCount() + 5); // Offset by the company info, title, header rows, and data rows
                    Cell sumCell = sumRow.createCell(jTable.getColumnCount() - 3); // Placed at the last cell of the last column
                    sumCell.setCellFormula(formula); // Assuming the data starts from row 6
                    // Date
                    LocalDate now = LocalDate.now();
                    Row dateRow = sheet.createRow(jTable.getRowCount() + 7); // Offset by the company info, title, header rows, data rows, and sum row
                    Cell dateCell = dateRow.createCell(jTable.getColumnCount() - 3); // Placed at the last cell of the last column
                    dateCell.setCellValue("Ngày " + now.getDayOfMonth()+ " tháng " + now.getMonthValue()+ " năm " + now.getYear());
                    // Author
                    Row authorRow = sheet.createRow(jTable.getRowCount() + 8); // Offset by the company info, title, header rows, data rows, sum row, and date row
                    Cell authorCell = authorRow.createCell(jTable.getColumnCount() - 3); // Placed at the last cell of the last column
                    authorCell.setCellValue("Người lập biểu");

                    // Write the workbook to the output stream
                    outputStream = new FileOutputStream(saveFile);
                    workbook.write(outputStream);
                }
                outputStream.close();

                openFile(saveFile.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }*/// </editor-fold> 
    public static void exportToExcel(JFileChooser jFileChooser, JTable jTable, String sheetName, String tableTitle) {
        try {
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                FileOutputStream outputStream;
                try (Workbook workbook = new XSSFWorkbook()) {
                    Sheet sheet = workbook.createSheet(sheetName);
                    // Title Style
                    CellStyle titleStyle = workbook.createCellStyle();
                    titleStyle.setAlignment(HorizontalAlignment.CENTER);
                    Font titleFont = workbook.createFont();
                    titleFont.setFontName("Times New Roman");
                    titleFont.setBold(true);
                    titleFont.setFontHeightInPoints((short) 16);
                    titleStyle.setFont(titleFont);
                    // Header Style
                    CellStyle headerCellStyle = workbook.createCellStyle();
                    headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
                    headerCellStyle.setFillForegroundColor(IndexedColors.PALE_BLUE.getIndex());
                    headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    headerCellStyle.setBorderTop(BorderStyle.THIN);
                    headerCellStyle.setBorderBottom(BorderStyle.THIN);
                    headerCellStyle.setBorderLeft(BorderStyle.THIN);
                    headerCellStyle.setBorderRight(BorderStyle.THIN);
                    Font headerFont = workbook.createFont();
                    headerFont.setFontName("Times New Roman");
                    headerFont.setBold(true);
                    headerCellStyle.setFont(headerFont);
                    // Data Style
                    CellStyle dataCellStyle = workbook.createCellStyle();
                    Font dataFont = workbook.createFont();
                    dataFont.setFontName("Times New Roman");
                    dataCellStyle.setFont(dataFont);
                    dataCellStyle.setBorderTop(BorderStyle.THIN);
                    dataCellStyle.setBorderBottom(BorderStyle.THIN);
                    dataCellStyle.setBorderLeft(BorderStyle.THIN);
                    dataCellStyle.setBorderRight(BorderStyle.THIN);

                    // Create a data format for numbers
                    DataFormat dataFormat = workbook.createDataFormat();
                    CellStyle numberCellStyle = workbook.createCellStyle();
                    numberCellStyle.cloneStyleFrom(dataCellStyle);
                    numberCellStyle.setDataFormat(dataFormat.getFormat("###0")); // Customize the format as needed

                    // Company Info
                    CellStyle inforCellStyle = workbook.createCellStyle();
                    inforCellStyle.setAlignment(HorizontalAlignment.LEFT);
                    org.apache.poi.ss.usermodel.Font inforFont = workbook.createFont();
                    inforFont.setItalic(true);
                    inforFont.setFontName("Times New Roman");
                    inforCellStyle.setFont(inforFont);
                    Row companyInfoRow1 = sheet.createRow(0);
                    Cell companyNameCell = companyInfoRow1.createCell(0);
                    companyNameCell.setCellValue("CÔNG TY CỔ PHẦN TINASOFT VIỆT NAM");
                    companyNameCell.setCellStyle(inforCellStyle);
                    Row companyInfoRow2 = sheet.createRow(1);
                    Cell companyAddressCell = companyInfoRow2.createCell(0);
                    companyAddressCell.setCellValue("Số 110 Đường Trần Phú, Phường Mộ Lao, Quận Hà Đông, Thành phố Hà Nội");
                    companyAddressCell.setCellStyle(inforCellStyle);
                    Row companyInfoRow3 = sheet.createRow(2);
                    Cell companyHotlineCell = companyInfoRow3.createCell(0);
                    companyHotlineCell.setCellValue("SDT: 0976703384");
                    companyHotlineCell.setCellStyle(inforCellStyle);
                    // Title Row
                    Row titleRow = sheet.createRow(3);
                    Cell titleCell = titleRow.createCell(0);
                    titleCell.setCellValue(tableTitle);
                    titleCell.setCellStyle(titleStyle);
                    sheet.addMergedRegion(new CellRangeAddress(3, 3, 0, jTable.getColumnCount() - 1));
                    // Header Row
                    Row headerRow = sheet.createRow(4);
                    for (int i = 0; i < jTable.getColumnCount(); i++) {
                        Cell cell = headerRow.createCell(i);
                        cell.setCellValue(jTable.getColumnName(i));
                        cell.setCellStyle(headerCellStyle);
                    }
                    // Populating data and applying formatting for each cell
                    for (int j = 0; j < jTable.getRowCount(); j++) {
                        Row row = sheet.createRow(j + 5); // Offset by 5 to skip the company info, title, and header rows
                        for (int k = 0; k < jTable.getColumnCount(); k++) {
                            Cell cell = row.createCell(k);
                            if (jTable.getValueAt(j, k) != null) {
                                if (jTable.getValueAt(j, k) instanceof Number) {
                                    cell.setCellValue(((Number) jTable.getValueAt(j, k)).doubleValue());
                                    cell.setCellStyle(numberCellStyle); // Apply number cell style
                                } else {
                                    cell.setCellValue(jTable.getValueAt(j, k).toString());
                                    cell.setCellStyle(dataCellStyle);
                                }
                            }
                        }
                    }
                    // Auto-size columns (optional)
                    for (int i = 1; i < jTable.getColumnCount(); i++) {
                        sheet.autoSizeColumn(i);
                    }
//                    // Sum function
//                    Row sumRow = sheet.createRow(jTable.getRowCount() + 5); // Offset by the company info, title, header rows, and data rows
//                    Cell sumCell = sumRow.createCell(jTable.getColumnCount() - 2); // Placed at the last cell of the last column
//                    sumCell.setCellFormula(formula); // Assuming the data starts from row 6
                    // Date Style
                    CellStyle creditCellStyle = workbook.createCellStyle();
                    creditCellStyle.setAlignment(HorizontalAlignment.CENTER);
                    creditCellStyle.setFont(inforFont);

                    // Date
                    LocalDate now = LocalDate.now();
                    Row dateRow = sheet.createRow(jTable.getRowCount() + 6); // Offset by the company info, title, header rows, data rows, and sum row
                    Cell dateCell = dateRow.createCell(jTable.getColumnCount() - 2); // Placed at the last cell of the last column
                    dateCell.setCellValue("Ngày " + now.getDayOfMonth() + " tháng " + now.getMonthValue() + " năm " + now.getYear());
                    dateCell.setCellStyle(creditCellStyle);

                    // Author
                    Row authorRow = sheet.createRow(jTable.getRowCount() + 7); // Offset by the company info, title, header rows, data rows, sum row, and date row
                    Cell authorCell = authorRow.createCell(jTable.getColumnCount() - 2); // Placed at the last cell of the last column
                    authorCell.setCellValue("Người lập biểu");
                    authorCell.setCellStyle(creditCellStyle);

                    // Write the workbook to the output stream
                    outputStream = new FileOutputStream(saveFile);
                    workbook.write(outputStream);
                }
                outputStream.close();

                openFile(saveFile.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static Date getDateFromDateChooser(JDateChooser dateChooser) {
        Date date = null;
        if (dateChooser.getDate() != null) {
            date = new Date(dateChooser.getDate().getTime());
        }
        return date;
    }

    public static String dateConverter(java.util.Date sqlDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = simpleDateFormat.format(sqlDate);
        return formattedDate;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean checkAge(LocalDate ngaySinh) {
        LocalDate ngayHienTai = LocalDate.now();
        Period period = Period.between(ngaySinh, ngayHienTai);
        return period.getYears() >= 18;
    }
}
