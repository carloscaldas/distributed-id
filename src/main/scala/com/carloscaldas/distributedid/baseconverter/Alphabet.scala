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

case class Alphabet(characterSet: String) {

  final val radix: Int = characterSet.length
  final val mapCharToDecimalValue: Map[Char, Int] = characterSet.zipWithIndex.toMap
  final val mapDecimalValueToChar: Map[Int, Char] = characterSet.zipWithIndex.map {
    case (character, value) => (value, character)
  }.toMap

}

object Alphabet {
  val DECIMAL = new Alphabet("0123456789")
  val OCTAL = new Alphabet("01234567")
  val HEX = new Alphabet("0123456789ABCDEF")
  val BIN = new Alphabet("01")
  val CAPITAL_ENGLISH_ALPHA = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ")
}
