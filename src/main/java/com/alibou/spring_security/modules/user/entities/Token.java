package com.alibou.spring_security.modules.user.entities;

import com.alibou.spring_security.base.entities.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens", uniqueConstraints = {
        @UniqueConstraint(columnNames = "token")
})
@Schema(description = "User Model Information")
public class Token extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Tutorial Id", example = "1")
    private Integer id;

    private String token;

    private Integer userId;
}
