import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The purpose of this class if to save and rename a file from a MultipartFile.
 * @author Rayner MDZ
 */
public class FileUploader {

  private static final Pattern pattern = Pattern.compile(".[a-zA-Z0-9]+");

  /**
   * Handles all the methods to upload and rename a file.
   * @param directory as the path where the file will be saved.
   * @param multipartFile as the file coming from the frontend.
   * @param name as the desired name for the file.
   * @return a boolean specifying if the upload was successful.
   */
  public static boolean pictureUploader(String directory, MultipartFile multipartFile, String name) {

    File file = fileSaver(directory, multipartFile, name);

    try {
      fileNameChanger(multipartFile, file);
      return true;
    } catch (NoSuchElementException e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Saves a new empty file with 0 bytes. Assigns the name provided.
   * The file is saved to the directory specified.
   * @param dir referring to directory where the file will be saved.
   * @param multipartFile as the file coming from the user.
   * @param name : This is the name new name assigned to the file created.
   * @return the file saved.
   */
  public static File fileSaver(String dir, MultipartFile multipartFile, String name) {

    String originalName = multipartFile.getOriginalFilename();
    String ext = getFileExtension(originalName);
    String extension = getFileExtensionRegex(originalName);

    // Creates a new empty file with the name provided and the same extension of the passed file.
    // File convertFile  = new File(DirCreator.UPLOAD_DIRECTORY + newName + ext);
    File convertedFile = new File(dir + name + extension);

    // Saves the empty file.
    try {
      final boolean newFile = convertedFile.createNewFile();
      if (newFile) {
        return convertedFile;
      }
      throw new IOException("Could not save empty file!");
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Takes the Multipart file from the user. Then, takes the empty file already created.
   * With the empty file, generates a FileOutputStream.
   * Lastly, writes the Multipart File data to the FileOutputStream.
   * @param multipartFile as the file coming from the user.
   * @param file as the empty file already created.
   */
  public static void fileNameChanger(MultipartFile multipartFile, File file) {

    // Creates a file stream of the file created.
    FileOutputStream fileOutputStream = null;
    try {
      fileOutputStream = new FileOutputStream(file);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    // If the stream of files is not null then it passes the data of the file passed.
    if (fileOutputStream != null) {
      try {
        fileOutputStream.write(multipartFile.getBytes());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    // Closes the file stream.
    try {
      if (fileOutputStream != null) {
        fileOutputStream.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Removes all characters before the last 'DOT' from the name.
   * @param name as the file name
   * @return the extension of the file.
   */
  public static String getFileExtension(String name) {

    String extension;
    try {
      extension = name.substring(name.lastIndexOf("."));

    } catch (Exception e) {
      extension = "";
    }
    return extension;
  }

  /**
   * Removes all characters before the last 'DOT' from the name.
   * @param name as the file name.
   * @return the extension of the file
   */
  public static String getFileExtensionRegex(String name) {

    String extension = null;
    Matcher m1 = null;

    if (name != null) {
      m1 = pattern.matcher(name);
    }

    if (m1 != null) {
      while (m1.find()) {
        extension = m1.group();
      }
    }
    return extension;
  }
}
