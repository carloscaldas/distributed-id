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

package com.carloscaldas.distributedid.uniqueid

import java.time.{LocalDateTime, ZoneOffset}

import com.carloscaldas.distributedid.baseconverter.{Alphabet, NumberConverter}

case class UniqueIdSerializerConfig(range: GenericRange[LocalDateTime], alphabet: Alphabet, valueSize: Int) {

  private val MinLengthSize: Int = calculateRepresentationSize(range.start)
  private val MaxLengthSize: Int = calculateRepresentationSize(range.end)
  val DateTimeLengthSize: Int = MinLengthSize.max(MaxLengthSize)

  private def calculateRepresentationSize(dateTime: LocalDateTime): Int = {
    val dateTimeInUtcMillis = dateTime.atOffset(ZoneOffset.UTC).toInstant.toEpochMilli.toString
    val dateTimeInUtcMillisInTargetAlphabetBase = NumberConverter.convert(Alphabet.DECIMAL, alphabet, dateTimeInUtcMillis)
    dateTimeInUtcMillisInTargetAlphabetBase.length
  }

}
