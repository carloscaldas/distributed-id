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

import java.time.Instant

trait UniqueId {
  def dc: Char

  def serializer: UniqueIdSerializer

  def instantCreation: Instant

  def randomValue: String

  val value: String = {
    dc + serializer.dateTimeAsString(instantCreation) + randomValue
  }
}

