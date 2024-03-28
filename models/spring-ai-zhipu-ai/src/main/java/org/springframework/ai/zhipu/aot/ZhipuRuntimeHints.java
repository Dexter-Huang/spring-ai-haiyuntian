package org.springframework.ai.zhipu.aot;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

/**
 * @author huangminglang
 * @version 1.0
 * @description: TODO
 * @date 2024/3/28 10:29
 */
public class ZhipuRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        var mcs = MemberCategory.values();
        // TODO
    }
}
