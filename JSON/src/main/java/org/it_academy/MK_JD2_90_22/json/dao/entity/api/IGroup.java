package org.it_academy.MK_JD2_90_22.json.dao.entity.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.it_academy.MK_JD2_90_22.json.dao.entity.builders.GroupBuilder;

@JsonDeserialize(builder = GroupBuilder.class)
public interface IGroup {

    long getId();

    String getName();
}
