package knoldus.assignment

import org.scalatest.FunSuite



class ConvertToUpperCaseTest extends FunSuite {

  val convertUpperCaseObj = new ConvertToUpperCase

  test("Testing upperCase for all extensions")
  {
    assert(convertUpperCaseObj.upperCase("src/test/testFiles", "src/test/CapitalizeOutput", "all"))
  }

  test("Testing upperCase for selected extensions")
  {
    assert(convertUpperCaseObj.upperCase("src/test/testFiles", "src/test/CapitalizeOutput", ".log"))
  }

  test("Testing upperCase for invalid extension")
  {
    intercept[Exception]
    {
      convertUpperCaseObj.upperCase("src/test/testFiles", "src/test/CapitalizedOutput", ".invalid")
    }
  }
}
