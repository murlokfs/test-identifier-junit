package testIdentifier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Testes automatizados do programa Identifier.
 * Criterios: Particionamento em Classes de Equivalencia e Analise de Valor Limite.
 * Cada teste segue a estrutura Setup / Invocation / Assessment.
 *
 * Aluno: Murilo Kauan Fontes da Silva
 */
@DisplayName("Programa Identifier")
class IdentifierTest {

    private Identifier identifier;

    // SETUP comum: nova instancia do objeto sob teste antes de cada caso
    @BeforeEach
    void setUp() {
        identifier = new Identifier();
    }

    // ============================================================
    // Particionamento em Classes de Equivalencia
    // ============================================================
    @Nested
    @DisplayName("Classes de Equivalencia")
    class ClassesDeEquivalencia {

        @Test
        @DisplayName("CT01 - CV1, CV2, CV3: 'a1b2' -> Valido")
        void ct01_identificadorValido() {
            // Setup
            String entrada = "a1b2";
            // Invocation
            boolean resultado = identifier.validateIdentifier(entrada);
            // Assessment
            assertTrue(resultado, "Deveria ser valido");
        }

        @Test
        @DisplayName("CT02 - CI1: '' (vazio) -> Invalido")
        void ct02_comprimentoZero() {
            // Setup
            String entrada = "";
            // Invocation
            boolean resultado = identifier.validateIdentifier(entrada);
            // Assessment
            assertFalse(resultado, "String vazia deveria ser invalida");
        }

        @Test
        @DisplayName("CT03 - CI2: 'abcdefgh' (8 caracteres) -> Invalido")
        void ct03_comprimentoMaiorQueSeis() {
            // Setup
            String entrada = "abcdefgh";
            // Invocation
            boolean resultado = identifier.validateIdentifier(entrada);
            // Assessment
            assertFalse(resultado, "Mais de 6 caracteres deveria ser invalido");
        }

        @Test
        @DisplayName("CT04 - CI3: '1abc' (inicia com digito) -> Invalido")
        void ct04_primeiroCaractereNaoLetra() {
            // Setup
            String entrada = "1abc";
            // Invocation
            boolean resultado = identifier.validateIdentifier(entrada);
            // Assessment
            assertFalse(resultado, "Nao iniciar com letra deveria ser invalido");
        }

        @Test
        @DisplayName("CT05 - CI4: 'ab#c' (caractere especial) -> Invalido")
        void ct05_caractereInvalido() {
            // Setup
            String entrada = "ab#c";
            // Invocation
            boolean resultado = identifier.validateIdentifier(entrada);
            // Assessment
            assertFalse(resultado, "Caractere especial deveria ser invalido");
        }
    }

    // ============================================================
    // Analise de Valor Limite - comprimento
    // ============================================================
    @Nested
    @DisplayName("Valor Limite - Comprimento")
    class ValorLimiteComprimento {

        @ParameterizedTest(name = "{0}: \"{1}\" -> {2}")
        @CsvSource({
            "VL01 n=0, '',      false",
            "VL02 n=1, a,       true",
            "VL03 n=6, abcdef,  true",
            "VL04 n=7, abcdefg, false"
        })
        void limitesDeComprimento(String id, String entrada, boolean esperado) {
            // Setup: entrada e saida esperada vindas do @CsvSource
            // Invocation
            boolean resultado = identifier.validateIdentifier(entrada);
            // Assessment
            assertEquals(esperado, resultado, id + " falhou");
        }
    }

    // ============================================================
    // Analise de Valor Limite - faixas de caracteres (ASCII)
    // ============================================================
    @Nested
    @DisplayName("Valor Limite - Faixas de caracteres")
    class ValorLimiteCaracteres {

        @ParameterizedTest(name = "{0}: \"{1}\" -> {2}")
        @CsvSource(delimiter = '|', value = {
            "VL05 extremo A       | A  | true",
            "VL05 extremo Z       | Z  | true",
            "VL06 extremo a       | a  | true",
            "VL06 extremo z       | z  | true",
            "VL07 antes de A (@)  | @  | false",
            "VL08 depois de Z ([) | [  | false",
            "VL09 antes de a (`)  | `  | false",
            "VL10 depois de z ({) | {  | false",
            "VL11 extremo 0       | a0 | true",
            "VL11 extremo 9       | a9 | true",
            "VL12 antes de 0 (/)  | a/ | false",
            "VL13 depois de 9 (:) | a: | false"
        })
        void limitesDeFaixa(String id, String entrada, boolean esperado) {
            // Setup: entrada e saida esperada vindas do @CsvSource
            // Invocation
            boolean resultado = identifier.validateIdentifier(entrada);
            // Assessment
            assertEquals(esperado, resultado, id + " falhou");
        }
    }

    // ============================================================
    // Exemplos da especificacao e caso fora do dominio
    // ============================================================
    @Nested
    @DisplayName("Exemplos da especificacao")
    class ExemplosEspecificacao {

        @Test
        @DisplayName("'string' -> Valido")
        void exemploValido() {
            // Setup
            String entrada = "string";
            // Invocation
            boolean resultado = identifier.validateIdentifier(entrada);
            // Assessment
            assertTrue(resultado);
        }

        @Test
        @DisplayName("'stringmuitogrande' -> Invalido")
        void exemploInvalido() {
            // Setup
            String entrada = "stringmuitogrande";
            // Invocation
            boolean resultado = identifier.validateIdentifier(entrada);
            // Assessment
            assertFalse(resultado);
        }

        @Test
        @DisplayName("null -> lanca NullPointerException (fora da especificacao)")
        void entradaNula() {
            // Setup
            String entrada = null;
            // Invocation + Assessment
            assertThrows(NullPointerException.class,
                () -> identifier.validateIdentifier(entrada));
        }
    }
}
