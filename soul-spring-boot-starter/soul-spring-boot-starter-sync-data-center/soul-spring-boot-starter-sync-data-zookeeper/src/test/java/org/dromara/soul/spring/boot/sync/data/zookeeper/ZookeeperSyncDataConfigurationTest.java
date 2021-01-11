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

package org.dromara.soul.spring.boot.sync.data.zookeeper;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkTimeoutException;
import org.dromara.soul.sync.data.api.PluginDataSubscriber;
import org.dromara.soul.sync.data.api.SyncDataService;
import org.dromara.soul.sync.data.zookeeper.ZookeeperSyncDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * test case for {@link ZookeeperSyncDataConfiguration}.
 *
 * @author David Liu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                ZookeeperSyncDataConfiguration.class
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {
                "soul.sync.zookeeper.url=localhost:2181",
                "soul.sync.zookeeper.sessionTimeout=30000",
                "soul.sync.zookeeper.connectionTimeout=500"
        })
@EnableAutoConfiguration
@MockBean({PluginDataSubscriber.class, ZkClient.class})
public final class ZookeeperSyncDataConfigurationTest {

    @Autowired
    private ZookeeperConfig zookeeperConfig;

    @Autowired
    private SyncDataService syncDataService;

    /**
     * case to test {@link ZookeeperSyncDataConfiguration} to register bean {@link ZookeeperSyncDataService}.
     */
    @Test
    public void testZookeeperSyncDataConfigurationRegisterBeanSyncDataService() {
        assertNotNull(syncDataService);
        assertTrue(syncDataService instanceof ZookeeperSyncDataService);
    }

    /**
     * case to test {@link ZookeeperSyncDataConfiguration} to register bean {@link ZookeeperConfig}.
     */
    @Test
    public void testZookeeperSyncDataConfigurationRegisterBeanZookeeperConfig() {
        assertThat(zookeeperConfig.getUrl(), is("localhost:2181"));
        assertThat(zookeeperConfig.getSessionTimeout(), is(30000));
        assertThat(zookeeperConfig.getConnectionTimeout(), is(500));
    }

    /**
     * case to test {@link ZookeeperSyncDataConfiguration} build {@link ZkClient} should throw exception.
     */
    @Test(expected = ZkTimeoutException.class)
    public void testZookeeperSyncDataConfigurationInitializeZkClientConnectionTimeout() {
        new ZookeeperSyncDataConfiguration().zkClient(zookeeperConfig);
    }
}
