/*
 * Copyright 2017 roddrim.net
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *    http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

import net.roddrim.number5.tools.collect.N5Collections;
import org.junit.Assert;

import java.util.Collections;
import java.util.Iterator;

public class N5CollectionsTest {

    public void isNullOrEmptyTestNull() throws Exception {

        Assert.assertTrue(N5Collections.isNullOrEmpty(null));

    }

    public void isNullOrEmptyTestEmptyCollection() throws Exception {

        Assert.assertTrue(N5Collections.isNullOrEmpty(Collections.emptyList()));

    }

    public void isNullOrEmptyTestEmptyIterator() throws Exception {

        Iterator<Object> it = new Iterator<Object>() {

            @Override
            public Object next() {
                return null;
            }

            @Override
            public boolean hasNext() {
                return false;
            }


        };

        Assert.assertTrue(N5Collections.isNullOrEmpty(N5Collections.iterable(it)));

    }

    public void isNullOrEmptyTestSingleElement() throws Exception {


        Assert.assertFalse(N5Collections.isNullOrEmpty(Collections.singleton(null)));

    }

}
