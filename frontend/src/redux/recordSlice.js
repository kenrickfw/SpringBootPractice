import { createSlice } from '@reduxjs/toolkit'

const initialState = {
    records: null,
}

export const recordSlice = createSlice({
    name: 'record',
    initialState,
    reducers: {
        setRecord: (state, action) => {
            state.records = action.payload
        },
        createRecord: (state, action) => {
            state.records = [action.payload, ...state.records].sort((a, b) => new Date(b.date) - new Date(a.date))
        },
        deleteRecord: (state, action) => {
            state.records = state.records.filter(r => r.id !== action.payload.id) 
        }
    },
})

export const { setRecord, createRecord, deleteRecord } = recordSlice.actions

export default recordSlice.reducer