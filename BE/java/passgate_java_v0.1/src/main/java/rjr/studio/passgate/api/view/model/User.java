package rjr.studio.passgate.api.view.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import rjr.studio.passgate.api.view.model.type.TypeRole;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User extends BaseModel {
	
	public static final String _TYPE = User.class.getSimpleName();
	
	private String name;
	private String surname;
	private String username;
	private String password;
    private Set<TypeRole> roles;

}
