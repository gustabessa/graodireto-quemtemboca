export function commitName (context, name) {
  context.commit('setName', name)
}

export function commitAuthorization (context, authorization) {
  context.commit('setAuthorization', authorization)
}
