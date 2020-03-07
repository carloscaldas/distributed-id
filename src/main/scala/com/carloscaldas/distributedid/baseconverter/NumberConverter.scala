/*
Copyright [2020] [Carlos Caldas - https://github.com/carloscaldas/distributed-id]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
  limitations under the License.
*/

package com.carloscaldas.distributedid.baseconverter

import java.math.BigInteger

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

object NumberConverter {

  def asDecimal(alphabet: Alphabet, value: String): BigInt = {
    var num: BigInt = BigInteger.ZERO
    var power: BigInt = 1
    (value.length - 1 to 0 by -1).foreach { index =>
      val ch = value.charAt(index)
      if (ch == '-') {
        num *= -1
      } else {
        num += alphabet.mapCharToDecimalValue(ch) * power
        power *= alphabet.radix
      }
    }
    num
  }

  def toAlphabet(alpha: Alphabet, valueBase10: BigInt): String = {
    val result = ListBuffer.empty[Char]
    if (valueBase10 < 0) {
      updateOutputWithAlphabet(alpha, valueBase10 * -1, result)
      result.prepend('-')
    } else {
      updateOutputWithAlphabet(alpha, valueBase10, result)
    }
    result.mkString
  }

  def convert(source: Alphabet, target: Alphabet, value: String): String = {
    if (source == target) {
      value
    } else if (value.headOption.contains('-')) {
      val x = asDecimal(source, value.tail)
      "-" + toAlphabet(target, x)
    } else {
      val x = asDecimal(source, value)
      toAlphabet(target, x)
    }
  }

  @tailrec
  private def updateOutputWithAlphabet(alpha: Alphabet, valueBase10: BigInt, output: ListBuffer[Char]): Unit = {
    if (valueBase10 < alpha.radix) {
      output.prepend(alpha.mapDecimalValueToChar(valueBase10.toInt))
    } else {
      val remainder = (valueBase10 % alpha.radix).toInt
      output.prepend(alpha.mapDecimalValueToChar(remainder))
      updateOutputWithAlphabet(alpha, valueBase10 / alpha.radix, output)
    }
  }

}
