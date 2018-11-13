# UploadMaker

## Description
Rest API enabling storing and downloading files. It can store files of every extension. The files are downloaded from given URL address and stored in “uploads” folder.

## Adopted assumptions
- A user gives an extension of a file
- Files are downloaded asynchronously

## Configuration:
- In file application.properties set data of created database 
- Make sure that 9007 port is free to use, if not change it to another 

## Usage

### Saving a file:
#### Endpoint
http://localhost:9007/files [POST]
#### Description
Saving is possible singly or serially by setting a few files in request body. Each file must have an URL address and a name with extension. Files are downloaded asynchronously. The application gives response after saving all of the elements.
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
		"name": "1.html"	
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
  
### Downloading file:
#### Endpoint:
http://localhost:9007/files/download [GET] parameters: name
#### Description
Files are downloaded singly by giving the name with the extension.
#### Example:
http://localhost:9007/files/download?name=google.html
#### Validation:
Name:
- File with this name already exists in database

### Listing saved files and filtering results, paging and sorting:
#### Endpoint:
http://localhost:9007/files [GET] parameters: name, url, size, page, sort, ‘value’.dir (eg. name.dir)
#### Example:
http://localhost:9007/files?name=html&url=google&size=2&page=0&sort=name&name.dir=desc


