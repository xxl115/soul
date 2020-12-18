/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.dromara.soul.admin.vo;

import org.dromara.soul.admin.AbstractReflectGetterSetterTest;
import org.dromara.soul.admin.entity.SelectorConditionDO;
import org.dromara.soul.common.enums.OperatorEnum;
import org.dromara.soul.common.enums.ParamTypeEnum;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertNotNull;

/**
 * Test case for SelectorConditionVO.
 *
 * @author midnight2104
 */
public final class SelectorConditionVOTest extends AbstractReflectGetterSetterTest {

    @Override
    protected Class<?> getTargetClass() {
        return SelectorConditionVO.class;
    }

    @Test
    public void testBuildSelectorConditionVO() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        assertNotNull(SelectorConditionVO.buildSelectorConditionVO(SelectorConditionDO.builder()
                .paramType(ParamTypeEnum.POST.getName())
                .operator(OperatorEnum.MATCH.getAlias())
                .dateCreated(currentTime)
                .dateUpdated(currentTime)
                .build()));
    }
}
