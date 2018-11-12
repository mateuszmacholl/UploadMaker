package mateuszmacholl.uploadmaker.controller

import mateuszmacholl.uploadmaker.dto.ShowFileDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import spock.lang.Specification

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileControllerTest extends Specification {
    @Autowired
    private TestRestTemplate restTemplate

    private String path = "/files"

    def "get list of files"() {
        when:
        def response = restTemplate.getForEntity(path, String.class)
        then:
        HttpStatus.OK == response.statusCode
    }

    def "upload html files"() {
        given:
        def name = "test/file.html"
        def url = "https://bing.com"
        def name2 = "test/file2.html"
        def url2 = "https://yahoo.com"
        def files = [
                [
                        name: name,
                        url : url
                ],
                [
                        name: name2,
                        url : url2
                ]
        ]
        def body = [
                files: files
        ]
        when:
        def response = restTemplate.postForEntity(path, body, ShowFileDto[].class)
        then:
        HttpStatus.OK == response.statusCode
        response.body.length == 2
    }

    def "upload zip file"() {
        given:
        def name = "test/file.zip"
        def url = "https://notepad-plus-plus.org/repository/7.x/7.5.9/npp.7.5.9.bin.zip"
        def files = [
                [
                        name: name,
                        url : url
                ]
        ]
        def body = [
                files: files
        ]
        when:
        def response = restTemplate.postForEntity(path, body, ShowFileDto[].class)
        then:
        HttpStatus.OK == response.statusCode
        response.body.length != 0
    }

    def "download file"(){
        given:
        def name = "test/file.html"
        when:
        def response = restTemplate.getForEntity(path + "/download?name=" + name, Resource.class)
        then:
        HttpStatus.OK == response.statusCode
    }
}
