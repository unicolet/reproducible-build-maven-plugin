/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.zlika.reproducible;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link PomPropertiesStripper}.
 */
public class PomPropertiesStripperTest
{
    /**
     * Tests stripping on a reference pom.properties file.
     * @throws IOException 
     */
    @Test
    public void testStripPom() throws IOException
    {
        final File out = File.createTempFile("pom", null);
        out.deleteOnExit();
        
        new PomPropertiesStripper().strip(new File(this.getClass().getResource("pom.properties").getFile()), out);
        
        final byte[] expected = Files.readAllBytes(new File(
                                    this.getClass().getResource("pom-stripped.properties").getFile()).toPath());
        final byte[] actual = Files.readAllBytes(out.toPath());
        Assert.assertArrayEquals(expected, actual);
        out.delete();
    }
}
