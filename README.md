# UploadMaker

## Description
Rest API allowing to store and download any file, regardless of the extension. The files are downloaded from provided URL and stored in “uploads” folder.

## Adopted assumptions
- Extension of a file is provided by user
- Files are downloaded asynchronously

## Configuration
- Configure database at application.properties
- Make sure that 9007 port is free to use, if not change it to another 

## Usage

### Saving a file
#### Endpoint
http://localhost:9007/files [POST]
#### Description
Saving is possible singly or serially by setting a few files in request body. Each file must have an URL address and a name with extension. Files are downloaded asynchronously. The application gives a response after it has finished the given task.
#### Example
http://localhost:9007/files [POST]
```
{
	"files":
	[
		{
		"url": "https://google.com",
		"name": "google.html"	
		},
		{
		"url": "https://facebook.com/",
		"name": "fb.html"	
		}
	]
}
```
#### Validation
General:
  - Files on the list must have different names
  
Name:
  - Filled
  - Has an extension
  - File with this name does not exist in database
  
Url:
  - Filled
  - Url pattern
  
### Downloading file
#### Endpoint
http://localhost:9007/files/download [GET] parameters: name
#### Description
Files are downloaded singly by giving the name with the extension.
#### Example
http://localhost:9007/files/download?name=google.html
#### Validation
Name:
- File with this name already exists in database

### Listing saved files and filtering results, paging and sorting
#### Endpoint
http://localhost:9007/files [GET] parameters: name, url, size, page, sort, ‘value’.dir (eg. name.dir)
#### Example
http://localhost:9007/files?name=html&url=google&size=2&page=0&sort=name&name.dir=desc


