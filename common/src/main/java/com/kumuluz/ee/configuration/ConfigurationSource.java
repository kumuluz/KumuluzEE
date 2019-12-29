/*
 *  Copyright (c) 2014-2017 Kumuluz and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
*/
package com.kumuluz.ee.configuration;

import com.kumuluz.ee.configuration.utils.ConfigurationDispatcher;

import java.util.List;
import java.util.Optional;

/**
 * @author Tilen Faganel
 * @since 2.1.0
 */
public interface ConfigurationSource {

    String CONFIG_ORDINAL = "config_ordinal";

    void init(ConfigurationDispatcher configurationDispatcher);

    Optional<String> get(String key);

    default Optional<Boolean> getBoolean(String key) {

        Optional<String> value = get(key);

        return value.map(Boolean::valueOf);
    }

    default Optional<Integer> getInteger(String key) {

        Optional<String> value = get(key);

        if (value.isPresent()) {

            try {
                return Optional.of(Integer.valueOf(value.get()));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    default Optional<Long> getLong(String key) {

        Optional<String> value = get(key);

        if (value.isPresent()) {

            try {
                return Optional.of(Long.valueOf(value.get()));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    default Optional<Double> getDouble(String key) {

        Optional<String> value = get(key);

        if (value.isPresent()) {

            try {
                return Optional.of(Double.valueOf(value.get()));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    default Optional<Float> getFloat(String key) {

        Optional<String> value = get(key);

        if (value.isPresent()) {

            try {
                return Optional.of(Float.valueOf(value.get()));
            } catch (NumberFormatException e) {
                return Optional.empty();
            }

        } else {
            return Optional.empty();
        }
    }

    Optional<Integer> getListSize(String key);

    Optional<List<String>> getMapKeys(String key);

    void watch(String key);

    void set(String key, String value);

    void set(String key, Boolean value);

    void set(String key, Integer value);

    void set(String key, Double value);

    void set(String key, Float value);

    default Integer getOrdinal() {
        return getInteger(CONFIG_ORDINAL).orElse(100);
    }
}
