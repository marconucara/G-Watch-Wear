/*
 * Copyright (C) 2019 Juraj Antal
 *
 * Originally created in G-Watch App
 *
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

package sk.trupici.gwatch.wear.data;

import android.content.Context;

import java.util.Date;

import sk.trupici.gwatch.wear.R;
import sk.trupici.gwatch.wear.util.UiUtils;

public abstract class GlucosePacketBase extends PacketBase {

    protected final long receivedAt;
    protected final short glucoseValue;
    protected final long timestamp;

    public GlucosePacketBase(PacketType type, String source, short glucoseValue, long timestamp) {
        super(type, source);
        this.receivedAt = new Date().getTime();
        this.glucoseValue = glucoseValue;
        this.timestamp = timestamp;
    }

    @Override
    public String toText(Context context, String header) {
        StringBuffer text = new StringBuffer();
        if (header != null) {
            text.append(header).append("\n");
        }
        text.append(context.getString(R.string.packet_type, getType().name())).append("\n");
        text.append(context.getString(R.string.packet_source, getSource())).append("\n");
        text.append(context.getString(R.string.packet_received_at, UiUtils.formatTime(new Date(receivedAt)))).append("\n");
        text.append(context.getString(R.string.packet_timestamp, UiUtils.formatTimeOrNoData(timestamp))).append("\n");

        text.append(context.getString(R.string.packet_bg_value, Math.round(glucoseValue), UiUtils.convertGlucoseToMmolL(glucoseValue))).append("\n");
        return text.toString();
    }

    public long getReceivedAt() {
        return receivedAt;
    }

    public short getGlucoseValue() {
        return glucoseValue;
    }

    public long getTimestamp() {
        return timestamp;
    }
}