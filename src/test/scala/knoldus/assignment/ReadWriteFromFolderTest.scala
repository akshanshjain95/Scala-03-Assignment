package knoldus.assignment

import java.io.{File, FileNotFoundException}



class ReadWriteFromFolderTest extends org.scalatest.FunSuite {

  val obj = new ReadWriteFromFolder

  test("Testing read function with non-existsing file")
  {
    intercept[FileNotFoundException] {
      val file = new File("noFile.txt")
      obj.read(file)
    }
  }

  test("Testing read function with existing file")
  {
    val file = new File("src/test/testFiles/testRead.txt")
    assert(obj.read(file) == "test")
  }

  test("Testing write function with invalid directory path")
  {
      val file = new File("testWrite.txt")
    intercept[FileNotFoundException] {
      obj.write(file, "content", "/home/akshansh")
    }
  }

  test("Testing write function with valid directory path")
  {
    val file = new File("testWrite.txt")
    assert(obj.write(file, "content", "src/test/testFiles"))
  }
}
