%%
%% Copyright (C) 2010 by Tobias Roehm
%%
%% This program is free software; you can redistribute it and/or modify
%% it under the terms of the GNU General Public License as published by
%% the Free Software Foundation; either version 3 of the License, or
%% (at your option) any later version.
%%
%% This program is distributed in the hope that it will be useful,
%% but WITHOUT ANY WARRANTY; without even the implied warranty of
%% MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
%% GNU General Public License for more details.
%%
%% You should have received a copy of the GNU General Public License
%% along with this program.  If not, see <http://www.gnu.org/licenses/>.
%%

:- register_ros_package(knowrob_common).
:- register_ros_package(knowrob_srdl).

:- use_module(library('srdl2')).
:- use_module(library('knowrob_owl')).

:- rdf_db:rdf_register_ns(pr2,    'http://knowrob.org/kb/PR2.owl#', [keep(true)]).
:- rdf_db:rdf_register_ns(baxter, 'http://knowrob.org/kb/baxter.owl#', [keep(true)]).


:- owl_parser:owl_parse('package://knowrob_srdl/owl/srdl2-action.owl').

