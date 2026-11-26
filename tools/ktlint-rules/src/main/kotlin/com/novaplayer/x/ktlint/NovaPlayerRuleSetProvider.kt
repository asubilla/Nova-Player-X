package com.novaplayer.x.ktlint

import com.pinterest.ktlint.cli.ruleset.core.api.RuleSetProviderV3
import com.pinterest.ktlint.rule.engine.core.api.RuleProvider
import com.pinterest.ktlint.rule.engine.core.api.RuleSetId

class NovaPlayerRuleSetProvider : RuleSetProviderV3(id = RuleSetId(value = "NovaPlayer")) {
    override fun getRuleProviders(): Set<RuleProvider> = setOf(RuleProvider { CallLayoutRule() })
}
