package knoldus.assignment

import java.io.FileNotFoundException
import java.nio.file.NoSuchFileException

import org.scalatest.FunSuite



class UniqueWordsTest extends FunSuite {

  val uniqueWordsObj = new UniqueWords

  test("Testing uniqueWords with invalid path")
  {
    intercept[NullPointerException]
    {
      uniqueWordsObj.countUniqueWordsFileList("/home/invalid", "src/test/testFiles/UniqueWordCountOutput")
    }
  }

  test("Testing uniqueWords with valid path")
  {
    assert(uniqueWordsObj.countUniqueWordsFileList("src/test/testFiles", "src/test/WordCountOutput"))
  }

}
