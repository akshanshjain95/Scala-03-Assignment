package knoldus.assignment

import java.io.File


class UniqueWords extends ReadWriteFromFolder{

  def countUniqueWordsFileList(inputPath: String, outputPath: String): Boolean = {

    val fileList = new File(inputPath).listFiles().toList

    val checkResult = fileList.map(countUniqueWordsFile(_, outputPath))

    if(checkResult.contains(false))
      {
        false
      }
    else
      {
        true
      }

  }

  private def countUniqueWordsFile(file: File, outputPath: String): Boolean = {

    val fileContent = read(file).toLowerCase

    val regex = """[A-Za-z]+""".r

    val words = for {
      s <- regex.findAllIn(fileContent)
    } yield s

    val listOfWords = words.toList
    val mapOfWordCount = listOfWords.groupBy(identity).mapValues(_.size)

    write(file, mapOfWordCount.mkString("\n"), outputPath)
  }

}
