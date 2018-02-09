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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;

/**
 * Sorts a text file line by line.
 */
final class SortTextFileStripper implements Stripper
{
    private final Comparator<String> comparator;
    
    /**
     * Constructor.
     * @param comparator the comparator to use for sorting the lines.
     */
    public SortTextFileStripper(Comparator<String> comparator)
    {
        this.comparator = comparator;
    }
    
    @Override
    public void strip(File in, File out) throws IOException
    {
        try (final BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(out), StandardCharsets.UTF_8));
         final BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(in), StandardCharsets.UTF_8)))
        {
            reader.lines().sorted(comparator)
                .forEach(s ->
                {
                    try
                    {
                        writer.write(s);
                        writer.write("\r\n");
                    }
                    catch (IOException e)
                    {
                    }
                });
        }
    }
}
