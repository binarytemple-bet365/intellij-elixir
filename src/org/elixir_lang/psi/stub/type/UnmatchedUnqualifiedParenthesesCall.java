package org.elixir_lang.psi.stub.type;

import com.intellij.psi.stubs.StubElement;
import com.intellij.psi.stubs.StubInputStream;
import com.intellij.util.io.StringRef;
import org.elixir_lang.psi.ElixirUnmatchedUnqualifiedParenthesesCall;
import org.elixir_lang.psi.impl.ElixirUnmatchedUnqualifiedParenthesesCallImpl;
import org.elixir_lang.psi.stub.type.call.Stub;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class UnmatchedUnqualifiedParenthesesCall extends Stub<org.elixir_lang.psi.stub.UnmatchedUnqualifiedParenthesesCall, ElixirUnmatchedUnqualifiedParenthesesCall> {
    /*
     * Constructors
     */

    public UnmatchedUnqualifiedParenthesesCall(@NotNull String debugName) {
        super(debugName);
    }

    /*
     * Instance Methods
     */

    @Override
    public ElixirUnmatchedUnqualifiedParenthesesCall createPsi(@NotNull org.elixir_lang.psi.stub.UnmatchedUnqualifiedParenthesesCall stub) {
        return new ElixirUnmatchedUnqualifiedParenthesesCallImpl(stub, this);
    }

    @Override
    public org.elixir_lang.psi.stub.UnmatchedUnqualifiedParenthesesCall createStub(
            @NotNull ElixirUnmatchedUnqualifiedParenthesesCall psi,
            StubElement parentStub
    ) {
        return new org.elixir_lang.psi.stub.UnmatchedUnqualifiedParenthesesCall(
                parentStub,
                this,
                psi.resolvedModuleName(),
                psi.functionName(),
                psi.resolvedFinalArity(),
                psi.hasDoBlockOrKeyword(),
                psi.getName(),
                psi.canonicalNameSet()
        );
    }

    @NotNull
    @Override
    public org.elixir_lang.psi.stub.UnmatchedUnqualifiedParenthesesCall deserialize(
            @NotNull StubInputStream dataStream,
            StubElement parentStub
    ) throws IOException {
        return new org.elixir_lang.psi.stub.UnmatchedUnqualifiedParenthesesCall(
                parentStub,
                this,
                dataStream.readName(),
                dataStream.readName(),
                dataStream.readInt(),
                dataStream.readBoolean(),
                dataStream.readName(),
                readNameSet(dataStream)
        );
    }
}
