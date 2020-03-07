package com.carloscaldas.distributedid.baseconverter

import com.carloscaldas.distributedid.myid.AlphabetUtil
import org.scalatest.funsuite.AnyFunSuite

import scala.util.Random

class AlphabetUtilTest extends AnyFunSuite {

  test(s"buildRandomSequence always returns elements with sequenceSize and characters belongs to alphabet") {
    val alphabet = Alphabet.CAPITAL_ENGLISH_ALPHA

    (1 to alphabet.characterSet.length).foreach { _ =>
      val stringSize: Int = Random.nextInt(10)
      val result: Seq[Char] = AlphabetUtil.buildRandomSequence(alphabet, stringSize)
      println(s"size: [$stringSize]. value: [${result.mkString}]")

      assertResult(stringSize)(result.length)
      assert(result.forall(v => alphabet.characterSet.indexOf(v) >= 0))
    }
  }

}
