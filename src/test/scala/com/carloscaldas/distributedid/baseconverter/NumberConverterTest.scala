package com.carloscaldas.distributedid.baseconverter

import org.scalatest.funsuite.AnyFunSuite
import NumberConverterTest._

import scala.util.Random

class NumberConverterTest extends AnyFunSuite {

  private val bigNumberBaseAndValue10: BaseAndValue = BaseAndValue(Alphabet.DECIMAL, "18446744073709551615")

  private val bigNumber = MultipleView(
    Set(
      bigNumberBaseAndValue10,
      BaseAndValue(Alphabet.OCTAL, "1777777777777777777777"),
      BaseAndValue(Alphabet.HEX, "FFFFFFFFFFFFFFFF")
    )
  )

  private val bigNumberBasesNot10 = bigNumber.seq.filterNot(_.base == Alphabet.DECIMAL)

  // convert, toAlphabet
  test("asDecimal") {
    val expectedBase10PositiveBaseAndValue = bigNumberBaseAndValue10
    val expectedBase10PositiveValue = BigInt(expectedBase10PositiveBaseAndValue.value)
    val expectedBase10NegativeBaseAndValue = expectedBase10PositiveBaseAndValue.copy(value = "-".concat(expectedBase10PositiveBaseAndValue.value))
    val expectedBase10NegativeValue = BigInt(expectedBase10NegativeBaseAndValue.value)

    bigNumberBasesNot10.foreach {
      case BaseAndValue(base, value) =>
        assertResult(expectedBase10PositiveValue) {
          NumberConverter.asDecimal(base, value)
        }
        assertResult(expectedBase10NegativeValue) {
          NumberConverter.asDecimal(base, "-".concat(value))
        }
    }
  }

  test("asAlphabet") {
    bigNumberBasesNot10.foreach {
      case bv@BaseAndValue(base, _) =>
        assertResult(bv.asPositive) {
          NumberConverter.toAlphabet(base, BigInt(bigNumberBaseAndValue10.asPositive))
        }
        assertResult(bv.asNegative) {
          NumberConverter.toAlphabet(base, BigInt(bigNumberBaseAndValue10.asNegative))
        }
    }
  }

  test("can convert negative and positive numbers") {
    (-50 to 50).foreach { indexBase10 =>
      val numberAsBase2 = NumberConverter.toAlphabet(Alphabet.BIN, indexBase10)
      val numberAsBase16 = NumberConverter.toAlphabet(Alphabet.HEX, indexBase10)
      val numberConvertedFromBinToHex = NumberConverter.convert(Alphabet.BIN, Alphabet.HEX, numberAsBase2)
      val numberConvertedFromHexToBin = NumberConverter.convert(Alphabet.HEX, Alphabet.BIN, numberAsBase16)

      assertResult(numberAsBase2)(numberConvertedFromHexToBin)
      assertResult(numberAsBase16)(numberConvertedFromBinToHex)
    }
  }

  test("can convert numbers back and forth") {
    (0 to 1000).foreach { _ =>
      val base10Number = Random.nextLong()
      val numberAsCustomBase = NumberConverter.toAlphabet(Alphabet.CAPITAL_ENGLISH_ALPHA, BigInt(base10Number))

      assertResult(base10Number.toString)(
        NumberConverter.convert(Alphabet.CAPITAL_ENGLISH_ALPHA, Alphabet.DECIMAL, numberAsCustomBase)
      )
    }
  }


  test("can convert negative numbers") {
    val numberAsBase10Str = "-2919357440"
    val numberAsBase16Str = NumberConverter.convert(Alphabet.DECIMAL, Alphabet.HEX, numberAsBase10Str)
    assertResult("-AE01DC00") {
      numberAsBase16Str
    }

  }
}


object NumberConverterTest {

  case class BaseAndValue(base: Alphabet, value: String) {
    def asPositive: String = {
      value.headOption match {
        case Some('-') => value.tail
        case _ => value
      }
    }

    def asNegative: String = {
      value.headOption match {
        case Some('-') => value
        case _ => "-".concat(value)
      }
    }

  }

  case class MultipleView(seq: Set[BaseAndValue])

}