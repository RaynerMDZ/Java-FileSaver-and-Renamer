# Java File Saver and Renamer
This class allows you to upload, change the name, and generate a random name for a file.

The reason why I decided to make this class was because I was looking around the internet for information on how to change the name of a MultipartFile.

## List of methods.
### pictureUploader(String directory, MultipartFile multipartFile, String name)
	Handles all the methods to upload and rename a file.

### fileSaver(String dir, MultipartFile multipartFile, String name)
	saves a new empty file with 0 bytes. Assigns the name provided.
	The file is saved to the directory specified.

### fileNameChanger(MultipartFile multipartFile, File file)
	Takes the Multipart file from the user. Then, takes the empty file already created.
	With the empty file, generates a FileOutputStream.
	Lastly, writes the Multipart File data to the FileOutputStream.

### getFileExtension(String name)
	Removes all characters before the last 'DOT' from the name.

### getFileExtensionRegex(String name)
	Removes all characters before the last 'DOT' from the name. This time using a REGEX.

#### Do not hesitate if you want to contribute. Fork immediately.
