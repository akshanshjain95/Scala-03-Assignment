package knoldus.assignment

import java.io.{File, FileNotFoundException}
import org.scalatest.FunSuite



class SplitURLTest extends FunSuite {

  val splitURLObj = new SplitURL
  val RWObj = new ReadWriteFromFolder

  test("Testing splitFile with invalid input path")
  {
    intercept[FileNotFoundException]
    {
      splitURLObj.splitFile("/invalid", "src/test/SplitURLOutput")
    }
  }

  test("Testing splitFile with invalid output path")
  {
    intercept[FileNotFoundException] {
      splitURLObj.splitFile("src/test/testFiles/URLs.txt", "/home/invalid/knoldus")
    }
  }

  test("Testing with invalid URL")
  {
    splitURLObj.splitFile("src/test/testFiles/invalidURL.txt", "src/test/SplitURLOutput")
    val file = new File("src/test/SplitURLOutput/invalidURL.txt")
    val str = RWObj.read(file)
    assert(str == "Invalid URL Address\n")
  }

  test("Testing with URL with Query Parameters")
  {
    splitURLObj.splitFile("src/test/testFiles/URLWithQueryParams.txt", "src/test/SplitURLOutput")
    val file = new File("src/test/SplitURLOutput/URLWithQueryParams.txt")
    assert(RWObj.read(file) == "Protocol -> https\nHost -> google\n" +
      "Domain -> co.in\nQuery Params -> Map(gfe_rd -> cr, name -> akshansh," +
      " work -> knoldus, language -> scala)\n")
  }

  test("Testing with URL without Query Parameters")
  {
    splitURLObj.splitFile("src/test/testFiles/URLWithoutQueryParams.txt", "src/test/SplitURLOutput")
    val file = new File("src/test/SplitURLOutput/URLWithoutQueryParams.txt")
    assert(RWObj.read(file) == "Protocol -> https\nHost -> google\nDomain -> com\n")
  }

  test("Testing splitFile with collection of different URLs")
  {
    assert(splitURLObj.splitFile("src/test/testFiles/URLs.txt", "src/test/SplitURLOutput"))
  }

}
