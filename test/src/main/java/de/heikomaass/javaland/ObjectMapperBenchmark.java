/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package de.heikomaass.javaland;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;

import java.util.List;

@State(Scope.Thread)
public class ObjectMapperBenchmark {

    private ZahlungsInfo zahlungsInfo;

    private ObjectMapper objectMapper;

    @Setup(Level.Trial)
    public void setUp() {
        zahlungsInfo = new ZahlungsInfo();
        zahlungsInfo.setZahlungsmittel(List.of("ZAHLUNGSMITTEL_1", "ZAHLUNGSMITTEL_2"));

        objectMapper = new ObjectMapper();
    }

    @Benchmark
    @Measurement(iterations = 3)
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1)
    public ZahlungsInfoDto newObjectMapper() {
        return new ObjectMapper().convertValue(zahlungsInfo, ZahlungsInfoDto.class);
    }

    @Benchmark
    @Measurement(iterations = 3)
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 1)
    public ZahlungsInfoDto staticObjectMapper() {
        return objectMapper.convertValue(zahlungsInfo, ZahlungsInfoDto.class);
    }

}
