package org.fdroid.test

import org.fdroid.index.v2.AntiFeatureV2
import org.fdroid.index.v2.CategoryV2
import org.fdroid.index.v2.FileV2
import org.fdroid.index.v2.LocalizedTextV2
import org.fdroid.index.v2.MirrorV2
import org.fdroid.index.v2.ReleaseChannelV2
import org.fdroid.index.v2.RepoV2
import org.fdroid.test.TestUtils.getRandomList
import org.fdroid.test.TestUtils.getRandomString
import org.fdroid.test.TestUtils.orNull
import kotlin.random.Random

public object TestRepoUtils {

    public fun getRandomMirror(): MirrorV2 = MirrorV2(
        url = getRandomString(),
        location = getRandomString().orNull()
    )

    public fun getRandomLocalizedTextV2(size: Int = Random.nextInt(0, 23)): LocalizedTextV2 =
        buildMap {
            repeat(size) {
                put(getRandomString(4), getRandomString())
            }
        }

    public fun getRandomFileV2(sha256Nullable: Boolean = true): FileV2 = FileV2(
        name = getRandomString(),
        sha256 = getRandomString(64).also { if (sha256Nullable) orNull() },
        size = Random.nextLong(-1, Long.MAX_VALUE)
    )

    public fun getRandomLocalizedFileV2(): Map<String, FileV2> =
        TestUtils.getRandomMap(Random.nextInt(1, 8)) {
            getRandomString(4) to getRandomFileV2()
        }

    public fun getRandomRepo(): RepoV2 = RepoV2(
        name = getRandomLocalizedTextV2(),
        icon = getRandomLocalizedFileV2(),
        address = getRandomString(),
        description = getRandomLocalizedTextV2(),
        mirrors = getRandomList { getRandomMirror() },
        timestamp = System.currentTimeMillis(),
        antiFeatures = TestUtils.getRandomMap {
            getRandomString() to AntiFeatureV2(
                icon = getRandomFileV2(),
                name = getRandomLocalizedTextV2(),
                description = getRandomLocalizedTextV2(),
            )
        },
        categories = TestUtils.getRandomMap {
            getRandomString() to CategoryV2(
                icon = getRandomFileV2(),
                name = getRandomLocalizedTextV2(),
                description = getRandomLocalizedTextV2(),
            )
        },
        releaseChannels = TestUtils.getRandomMap {
            getRandomString() to ReleaseChannelV2(
                name = getRandomLocalizedTextV2(),
                description = getRandomLocalizedTextV2(),
            )
        },
    )

}
