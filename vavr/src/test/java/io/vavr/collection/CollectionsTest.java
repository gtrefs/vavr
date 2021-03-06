/*                        __    __  __  __    __  ___
 *                       \  \  /  /    \  \  /  /  __/
 *                        \  \/  /  /\  \  \/  /  /
 *                         \____/__/  \__\____/__/.ɪᴏ
 * ᶜᵒᵖʸʳᶦᵍʰᵗ ᵇʸ ᵛᵃᵛʳ ⁻ ˡᶦᶜᵉⁿˢᵉᵈ ᵘⁿᵈᵉʳ ᵗʰᵉ ᵃᵖᵃᶜʰᵉ ˡᶦᶜᵉⁿˢᵉ ᵛᵉʳˢᶦᵒⁿ ᵗʷᵒ ᵈᵒᵗ ᶻᵉʳᵒ
 */
package io.vavr.collection;

import io.vavr.Tuple;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CollectionsTest {

    @Test
    public void shouldBeEqualSets() throws Exception {
        forAll(List.of(TreeSet.ofAll(1, 2, 3),
                HashSet.ofAll(1, 2, 3),
                LinkedHashSet.ofAll(1, 2, 3),
                BitSet.ofAll(1, 2, 3)), true);
    }

    @Test
    public void shouldNotBeEqualSets() throws Exception {
        forAll(List.of(HashSet.ofAll(1, 2, 3),
                HashSet.ofAll('a', 'b', 'c')), false);
    }

    @Test
    public void shouldBeEqualSeqs() throws Exception {
        forAll(List.of(Array.ofAll(1, 2, 3),
                Stream.ofAll(1, 2, 3),
                Vector.ofAll(1, 2, 3),
                List.ofAll(1, 2, 3),
                Queue.ofAll(1, 2, 3)), true);
    }

    @Test
    public void shouldNotBeEqualSeqs() throws Exception {
        forAll(List.of(Array.ofAll(1, 2, 3),
                Array.ofAll('a', 'b', 'c')), false);
    }

    @Test
    public void shouldBeEqualMaps() throws Exception {
        forAll(List.of(TreeMap.of(1, 2, 2, 3, 3, 4),
                HashMap.of(1, 2, 2, 3, 3, 4),
                LinkedHashMap.of(1, 2, 2, 3, 3, 4)), true);
    }

    @Test
    public void shouldNotBeEqualMaps() throws Exception {
        forAll(List.of(HashMap.of(1, 2, 2, 3, 3, 4),
                HashMap.of('a', 'b', 'c', 'd', 'e', 'f')), false);
    }

    @Test
    public void shouldBeEqualMultimaps() throws Exception {
        forAll(List.of(TreeMultimap.withSeq().<Integer, Integer>empty().put(1, 1).put(1, 1).put(2, 2),
                HashMultimap.withSeq().<Integer, Integer>empty().put(1, 1).put(1, 1).put(2, 2),
                LinkedHashMultimap.withSeq().<Integer, Integer>empty().put(1, 1).put(1, 1).put(2, 2)), true);
    }

    @Test
    public void shouldNotBeEqualMultimaps() throws Exception {
        forAll(List.of(TreeMultimap.withSeq().<Integer, Integer>empty().put(1, 1).put(1, 1).put(2, 2),
                HashMultimap.withSeq().<Character, Character>empty().put('a', 'b').put('c', 'd').put('e', 'f')),
                false);
    }

    @Test
    public void shouldNotBeEqualSeqAndSet() throws Exception {
        forAll(List.of(Array.ofAll(1, 2, 3),
                TreeSet.ofAll(1, 2, 3)), false);
    }

    @Test
    public void shouldNotBeEqualMapAndSet() throws Exception {
        forAll(List.of(HashSet.of(Tuple.of(1, 2), Tuple.of(2, 3)),
                TreeMap.of(1, 2, 2, 3)), false);
    }

    private void forAll(List<Traversable<?>> traversables, boolean value) {
        for (Traversable<?> traversable1 : traversables) {
            for (Traversable<?> traversable2 : traversables) {
                if (traversable1 != traversable2) {
                    assertThat(traversable1.equals(traversable2)).isEqualTo(value);
                    if (value) {
                        assertThat(traversable1.hashCode() == traversable2.hashCode()).isTrue();
                    }
                }
            }
        }
    }

}