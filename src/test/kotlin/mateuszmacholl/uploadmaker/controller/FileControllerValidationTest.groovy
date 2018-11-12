package mateuszmacholl.uploadmaker.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import spock.lang.Specification

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = AutoConfigureTestDatabase.Replace.ANY)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FileControllerValidationTest extends Specification {
    @Autowired
    private TestRestTemplate restTemplate

    private String path = "/files"

    def "upload files without name"() {
        given:
        def name = ""
        def url = "https://bing.com"
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
        def response = restTemplate.postForEntity(path, body, String.class)
        then:
        HttpStatus.BAD_REQUEST == response.statusCode
    }

    def "upload files without url"() {
        given:
        def name = "test/file1.html"
        def url = ""
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
        def response = restTemplate.postForEntity(path, body, String.class)
        then:
        HttpStatus.BAD_REQUEST == response.statusCode
    }

    def "upload files wrong url pattern"() {
        given:
        def name = "test/file1.html"
        def url = "abcdef"
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
        def response = restTemplate.postForEntity(path, body, String.class)
        then:
        HttpStatus.BAD_REQUEST == response.statusCode
    }

    def "upload files name without extension"() {
        given:
        def name = "test/file1"
        def url = "https://bing.com"
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
        def response = restTemplate.postForEntity(path, body, String.class)
        then:
        HttpStatus.BAD_REQUEST == response.statusCode
    }

    def "upload files the same names on list"() {
        given:
        def name = "test/file1.html"
        def url = "https://bing.com"
        def name2 = "test/file1.html"
        def url2 = "https://google.com"
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
        def response = restTemplate.postForEntity(path, body, String.class)
        then:
        HttpStatus.BAD_REQUEST == response.statusCode
    }
}
