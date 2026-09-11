package testIdentifier;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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
}
