package org.elixir_lang.beam;

import com.ericsson.otp.erlang.OtpErlangDecodeException;
import gnu.trove.THashMap;
import org.elixir_lang.beam.chunk.Atoms;
import org.elixir_lang.beam.chunk.Chunk;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.elixir_lang.beam.chunk.Chunk.TypeID.ATOM;
import static org.elixir_lang.beam.chunk.Chunk.length;
import static org.elixir_lang.beam.chunk.Chunk.typeID;

/**
 * See http://beam-wisdoms.clau.se/en/latest/indepth-beam-file.html
 */
public class Beam {
    /*
     * CONSTANTS
     */

    private static final String HEADER = "FOR1";

    /*
     * Fields
     */

    private Map<String, Chunk> chunkByTypeID;

    /*
     * Constructors
     */

    private Beam(@NotNull Collection<Chunk> chunkCollection) {
        chunkByTypeID = new THashMap<String, Chunk>(chunkCollection.size());

        for (Chunk chunk : chunkCollection) {
            chunkByTypeID.put(chunk.typeID, chunk);
        }
    }

    /*
     * Static Methods
     */

    @Nullable
    public static Beam from(@NotNull DataInputStream dataInputStream) throws IOException, OtpErlangDecodeException {
        if (!HEADER.equals(typeID(dataInputStream))) {
            return null;
        }

        length(dataInputStream);

        if (!"BEAM".equals(typeID(dataInputStream))) {
            return null;
        }

        List<Chunk> chunkList = new ArrayList<Chunk>();
        while (true) {
            Chunk chunk = Chunk.from(dataInputStream);

            if (chunk != null) {
                chunkList.add(chunk);
            } else {
                break;
            }
        }

        return new Beam(chunkList);
    }

    /*
     * Instance Methods
     */

    @Nullable
    public Atoms atoms() {
        Atoms atoms = null;

        Chunk chunk = chunk(ATOM);

        if (chunk != null) {
            atoms = Atoms.from(chunk);
        }

        return atoms;
    }

    @Nullable
    private Chunk chunk(@NotNull String typeID) {
        return chunkByTypeID.get(typeID);
    }

    @Nullable
    private Chunk chunk(@NotNull Chunk.TypeID typeID) {
        return chunk(typeID.toString());
    }
}