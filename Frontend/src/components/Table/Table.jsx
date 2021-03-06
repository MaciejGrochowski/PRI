import React from "react";
import MaterialTable from "material-table";
import TablePagination from "@material-ui/core/TablePagination";
import mystyle from "../../styles/tables.css";
import button from "../../styles/buttons.css"; //ToDo css or styles objects, pick one
import {Link} from "react-router-dom";

class Table extends React.Component {

    constructor() {
        super();
        this.state={
            rowsPerPage: 10,
            isDetails: false
        }
    }

    componentDidMount() {
    }


    componentDidUpdate(prevProps, prevState){
        let columnsConfig = this.props.columnsConfig

        if(this.props.notVisibleColumns){ //ToDo not mutate props!!
            columnsConfig = columnsConfig.filter(c => !this.props.notVisibleColumns.includes(c.title))
        }

        if(this.props.columnsConfig !== prevProps.columnsConfig && this.props.onDetailsClick) {
            columnsConfig.push(
                {
                    title: 'Detale',
                    field: '',
                    render: rowData => <Link to={this.props.detailsLink + "/" + rowData.id} className = "detaleButton auto-margin small" onClick={() => this.props.onDetailsClick(rowData)}>Detale</Link>,
                    sorting: false
                })
            this.setState({columnsConfig: columnsConfig})
        }
    }

    render(){
        const {data=[], noRecordsMessage="Brak danych", onChangeCountPerPage, ownOnChangePage, count, page, onOrderChange} = this.props;
        let columnsConfig = this.state.columnsConfig;

        const divStyle = {
            textDecoration: "none",
            backgroundColor: '#292F2F',
            color: '#FFD859',
            overflow: 'hidden',
            textOverflow: 'ellipsis',
            whiteSpace: 'nowrap',
        };

        const rowStyle = {
            textDecoration: "none",
            backgroundColor: '#292F2F',
            color: '#FFD859',
            width: '5%',
            // "&:hover": {
            //     backgroundColor: '#FFD859'
            //   }
        };
        return(
            <div styleName = {mystyle.table}>
                {/*ToDo this link shouldnt exist - it download icons with any render*/}
                <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
                <MaterialTable
                style={mystyle.table}
                localization={{
                    body: {
                        emptyDataSourceMessage: noRecordsMessage
                    },
                    pagination: {
                        firstAriaLabel: "Pierwsza strona",
                        firstTooltip: "Pierwsza strona",
                        previousTooltip: "Poprzednia strona",
                        nextTooltip: "Następna strona",
                        lastTooltip: "Ostatnia strona"

                    }
                }}
                onOrderChange={(orderedColumnId, orderDirection) => onOrderChange(orderedColumnId, orderDirection)}
                onSelectionChange={(rows) => this.props.onSelectionChange(rows)}

                components={{
                    // Row: props => (<div>{console.log(props)}<span>{props.data.name}</span><span>{props.data.surname}</span></div>),
                    Pagination: props => (
                        <TablePagination
                            {...props}
                            labelRowsPerPage={<div styleName="table">{props.labelRowsPerPage}</div>}
                            count={count}
                            onChangeRowsPerPage={event => {
                                props.onChangeRowsPerPage(event);
                                onChangeCountPerPage(event.target.value)
                            }}
                            page={page}
                            onChangePage={(event, page) => {
                            props.onChangePage(event, page);
                            ownOnChangePage(page)
                            }
                            }
                            labelDisplayedRows={row => <div
                                style={mystyle.labelDisplayedRows}>{props.labelDisplayedRows(row)}</div>}
                                SelectProps={{
                                style: divStyle,
                                }}/>)
                    }}


                columns={columnsConfig}
                data={data}

                options={{
                    selection: this.props.selection,
                    toolbar: false,
                    search: false,
                    paging: true,
                    filtering: false,
                    pageSize: this.state.rowsPerPage,
                    pageSizeOptions: [10, 30, 50],
                    paginationType: "stepped",
                    headerStyle: divStyle,
                    draggable: false
                }
                }


            />
            </div>


        )



    }


}


export default Table;